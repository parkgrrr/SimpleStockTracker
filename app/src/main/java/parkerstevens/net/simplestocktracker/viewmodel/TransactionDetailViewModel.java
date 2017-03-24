package parkerstevens.net.simplestocktracker.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.UUID;

import parkerstevens.net.simplestocktracker.BR;
import parkerstevens.net.simplestocktracker.data.ApiHelper;
import parkerstevens.net.simplestocktracker.data.StocksHelper;
import parkerstevens.net.simplestocktracker.model.Stock;
import parkerstevens.net.simplestocktracker.model.Transaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by pstev on 3/8/2017.
 */

public class TransactionDetailViewModel extends BaseObservable {
    private Context mContext;
    private String mName;
    private String mSymbol;
    private String mQuantity;
    private String mPrice;
    private String mFees;
    private Transaction mTransaction;
    private UUID mUUID;
    private Stock mStock;

    public TransactionDetailViewModel(String symbol, UUID uuid, Context context) {
        mContext = context;
        mSymbol = symbol;
        mUUID = uuid;

        if(StocksHelper.get(mContext).getTransaction(mUUID) == null)
        {
            mTransaction = new Transaction(uuid);
            mTransaction.setSymbol(symbol);
            mTransaction.setPrice(new BigDecimal(0));
            mTransaction.setFees(new BigDecimal(0));
            mTransaction.setQuantity(0);
        }
        else {
            mTransaction = StocksHelper.get(mContext).getTransaction(mUUID);
        }


        fetchStock();
    }

    @Bindable
    public String getSymbol() {
        return mSymbol;
    }

    @Bindable
    public String getQuantity() {
        return mQuantity;
    }

    public void setQuantity(String quantity) {
        if(!quantity.isEmpty()){
            mQuantity = quantity;
            mTransaction.setQuantity(Integer.parseInt(quantity));
            notifyPropertyChanged(BR.quantity);
        }

    }

    @Bindable
    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        if(!price.isEmpty()){
            mPrice = price;
            mTransaction.setPrice(new BigDecimal(price));
            notifyPropertyChanged(BR.price);
        }

    }

    @Bindable
    public String getFees() {
        return mFees;
    }

    public void setFees(String fees) {
        if (!fees.isEmpty()) {
            mFees = fees;
            mTransaction.setFees(new BigDecimal(fees));
            notifyPropertyChanged(BR.fees);
        }
    }


    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    public Transaction getTransaction() {
        return mTransaction;
    }

    public void addTransaction(StocksHelper stocksHelper){
        if(stocksHelper.getTransaction(mUUID) == null){
            stocksHelper.addTransaction(mTransaction);
        }
        else {
            stocksHelper.updateTransaction(mTransaction);
        }
    }

    public void fetchStock(){
        final StocksHelper stocksHelper = StocksHelper.get(mContext);
        ApiHelper.MarkitOnDemandApiInterface markitApi = ApiHelper.setupRetrofit();
        Calendar rightNow = Calendar.getInstance();

        Stock quote = stocksHelper.getStockQuote(mSymbol);

        if(quote != null){
            Calendar expireTime = quote.getCreateTime();
            expireTime.add(Calendar.MINUTE, 1);
            if(rightNow.before(expireTime)) {
                setName(quote.getName());
                return;
            }
        }else{
            Call<Stock> call = markitApi.getQuote(mSymbol);
            call.enqueue(new Callback<Stock>() {
                @Override
                public void onResponse(Call<Stock> call, Response<Stock> response) {
                    if(response.isSuccessful() && response.body().getStatus().equals("SUCCESS")){
                        Stock stock = response.body();
                        //populate view model
                        setName(stock.getName());
                        Log.i(TAG, "onresponse exec for " + stock.getName());
                        //add fresh stock to the db
                        stocksHelper.addStockQuote(stock);
                        Log.i(TAG, stock.getSymbol() + " has been added to the db");
                    } else {
                        Log.i(TAG,"Bad response for "+ mSymbol);

                        Stock stock = new Stock();
                        stock.setName("unable to get quote");
                        setName("unable to get quote");
                    }
                }

                @Override
                public void onFailure(Call<Stock> call, Throwable t) {
                    Log.e(TAG, "Failed API call", t);
                    setName("Failed to get quote");
                }
            });

        }



    }
}
