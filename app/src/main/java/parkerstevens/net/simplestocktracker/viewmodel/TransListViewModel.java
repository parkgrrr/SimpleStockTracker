package parkerstevens.net.simplestocktracker.viewmodel;


import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

import parkerstevens.net.simplestocktracker.BR;
import parkerstevens.net.simplestocktracker.data.ApiHelper;
import parkerstevens.net.simplestocktracker.data.StocksHelper;
import parkerstevens.net.simplestocktracker.model.Stock;
import parkerstevens.net.simplestocktracker.model.Transaction;
import parkerstevens.net.simplestocktracker.view.LookupDialogFragment;
import parkerstevens.net.simplestocktracker.view.TransListFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pstev on 3/7/2017.
 */

public class TransListViewModel extends BaseObservable {
    private final String TAG = "TransListViewModel";
    private FragmentManager mFragmentManager;
    private Context mContext;
    private Boolean isListLoading;

    public TransListViewModel(FragmentManager fragmentManager, Context context) {
        mFragmentManager = fragmentManager;
        mContext = context;
    }

    @Bindable
    public Boolean getListLoading() {
        return isListLoading;
    }

    public void setListLoading(Boolean listLoading) {
        isListLoading = listLoading;
        notifyPropertyChanged(BR.listLoading);
    }

    public ItemTouchHelper createItemTouchHelper(final TransListFragment.StockAdapter stockAdapter){
        ItemTouchHelper iTH = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                stockAdapter.onItemDismiss(viewHolder.getAdapterPosition());
            }
        });
        return iTH;
    }


    public void fabOnClick(){
        DialogFragment df = new LookupDialogFragment();
        df.show(mFragmentManager,"dialog_search");
        Log.v(TAG, "FAB clicked");
    }


    public void compareStocks(Transaction trans, Stock stock, Calendar rightNow){


    }




    public void fetchStocks(final TransListFragment.StockAdapter stockAdapter){

        final StocksHelper stocksHelper = StocksHelper.get(mContext);
         ApiHelper.MarkitOnDemandApiInterface markitApi = ApiHelper.setupRetrofit();

        List<Transaction> transactions = stocksHelper.getTransactions();
        List<Stock> quotes = stocksHelper.getStockQuotes();

        Calendar rightNow = Calendar.getInstance();

        for ( final Transaction trans:
                transactions) {

            //resets check at beginning of each transaction iteration
            Boolean isQuoteInDb = false;
            setListLoading(true);

            for (Stock stock:
                    quotes) {

                Calendar expireTime = stock.getCreateTime();
                expireTime.add(Calendar.MINUTE, 1);

                //Doesn't run api call if this is true
                if(trans.getSymbol().equals(stock.getSymbol()) && rightNow.before(expireTime)){
                    stockAdapter.addStockToList(stock, trans);
                    Log.i(TAG, stock.getSymbol() + " was found and recent in the db. Item created.");
                    isQuoteInDb = true;
                    setListLoading(false);
                    break;
                }
                else if(trans.getSymbol().equals(stock.getSymbol())){
                    //Delete stale stock from the db
                    stocksHelper.deleteStockQuote(stock.getSymbol());
                    Log.i(TAG, stock.getSymbol() + "is stale and deleted from the db");
                    break;
                }
            }

            if(!isQuoteInDb){
                Call<Stock> call = markitApi.getQuote(trans.getSymbol());
                call.enqueue(new Callback<Stock>() {
                    @Override
                    public void onResponse(Call<Stock> call, Response<Stock> response) {
                        if(response.isSuccessful() && response.body().getStatus().equals("SUCCESS")){
                            Stock stock = response.body();
                            stockAdapter.addStockToList(stock, trans);
                            Log.i(TAG, "onresponse exec for " + stock.getName());
                            //add fresh stock to the db
                            stocksHelper.addStockQuote(stock);
                            Log.i(TAG, stock.getSymbol() + " has been added to the db");
                        } else {
                            Log.i(TAG,"Bad response for "+ trans.getSymbol());

                            Stock stock = new Stock();
                            stock.setName("unable to get quote");
                            stock.setSymbol(trans.getSymbol());
                            stockAdapter.addStockToList(stock, trans);
                        }
                        setListLoading(false);
                    }

                    @Override
                    public void onFailure(Call<Stock> call, Throwable t) {
                        Log.e(TAG, "Failed API call", t);
                        Stock stock = new Stock();
                        stock.setName("Failed to get quote");
                        stock.setSymbol(trans.getSymbol());
                        stockAdapter.addStockToList(stock, trans);
                        setListLoading(false);
                    }
                });

            }




        }
    }
}

//// TODO: 3/28/2017 display a snackbar when list is empty

//// TODO: 3/28/2017 put fetch stocks loop on another thread, or pull the comparison algo into a new method
