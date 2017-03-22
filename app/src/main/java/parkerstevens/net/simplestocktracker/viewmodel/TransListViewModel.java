package parkerstevens.net.simplestocktracker.viewmodel;


import android.content.Context;
import android.databinding.BaseObservable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.List;

import parkerstevens.net.simplestocktracker.data.ApiHelper;
import parkerstevens.net.simplestocktracker.data.StocksHelper;
import parkerstevens.net.simplestocktracker.model.Stock;
import parkerstevens.net.simplestocktracker.model.Transaction;
import parkerstevens.net.simplestocktracker.view.StockSearchDialogFragment;
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

    public TransListViewModel(FragmentManager fragmentManager, Context context) {
        mFragmentManager = fragmentManager;
        mContext = context;
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
       // FragmentManager fm = new F
        DialogFragment df = new StockSearchDialogFragment();
        df.show(mFragmentManager,"dialog_search");
        Log.v(TAG, "FAB clicked");
    }






    public void fetchStocks(final TransListFragment.StockAdapter stockAdapter){
         ApiHelper.MarkitOnDemandApiInterface markitApi = ApiHelper.setupRetrofit();
        List<Transaction> quotes = StocksHelper.get(mContext).getTransactions();

        for ( final Transaction trans:
                quotes) {
            Call<Stock> call = markitApi.getQuote(trans.getSymbol());
            call.enqueue(new Callback<Stock>() {
                @Override
                public void onResponse(Call<Stock> call, Response<Stock> response) {
                    if(response.isSuccessful() && response.body().getStatus().equals("SUCCESS")){
                        Stock stock = response.body();
                        stockAdapter.addStockToList(stock, trans);
                        Log.i(TAG, "onresponse exec for " + stock.getName());
                    } else {
                        Log.i(TAG,"Bad respose for "+ trans.getSymbol());

                        Stock stock = new Stock();
                        stock.setName("Quote Unavailable");
                        stock.setSymbol(trans.getSymbol());
                        stockAdapter.addStockToList(stock, trans);
                    }
                }

                @Override
                public void onFailure(Call<Stock> call, Throwable t) {
                    Log.e(TAG, "Failed API call", t);
                    Stock stock = new Stock();
                    stock.setName("Failed to get quote");
                    stock.setSymbol(trans.getSymbol());
                    stockAdapter.addStockToList(stock, trans);
                }
            });
        }
    }
}
