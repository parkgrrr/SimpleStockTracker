package parkerstevens.net.simplestocktracker.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
            mTransaction.setPrice("");
            mTransaction.setFees("");
           // mTransaction.setQuantity(0);
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
        if(mTransaction.getQuantity() == 0){
            return "";
        }
        else {
            return mTransaction.getQuantity() + "";
        }

    }

    public void setQuantity(String quantity) {
        if(!quantity.isEmpty()){
            mTransaction.setQuantity(Integer.parseInt(quantity));
            notifyPropertyChanged(BR.quantity);
            notifyPropertyChanged(BR.holdingsChangeDollars);
        }
        else {
            mTransaction.setQuantity(0);
        }
    }

    @Bindable
    public String getPrice() {
        return mTransaction.getPrice();
    }

    public void setPrice(String price) {
        if(!price.isEmpty()){
            mTransaction.setPrice(price);
            notifyPropertyChanged(BR.price);
            notifyPropertyChanged(BR.holdingsChangeDollars);
        } else {
            mTransaction.setPrice("");
        }

    }

    @Bindable
    public String getFees() {
        return mTransaction.getFees();
    }

    public void setFees(String fees) {
        if (!fees.isEmpty()) {
            mTransaction.setFees(fees);
            notifyPropertyChanged(BR.fees);
            notifyPropertyChanged(BR.holdingsChangeDollars);
        } else {
            mTransaction.setFees("");
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

    public Stock getStock(){return mStock;}

    public String getPercentChange(){
        if(mStock != null){
            String addChar = "+";
            if(mStock.getChangePercent() < 0){
                addChar = "";
            }
            return "("+ addChar + Math.floor(mStock.getChangePercent() * 100)/100 + "%)";
        }
        return "";
    }

    public String getLastPrice(){
        if(mStock!= null){
            DecimalFormat df = new DecimalFormat("###,###,###,##0.00");
            return "$" + df.format(mStock.getLastPrice());
        }
        return "";
    }

    public String getChange(){
        if(mStock != null){
            DecimalFormat df = new DecimalFormat("###,###,###,##0.00");
            String addChar = "+";
            if(mStock.getChange().doubleValue() < 0){
                addChar = "";
            }
            return addChar + df.format(mStock.getChange());
        }
        Toast.makeText(mContext, "Unable to get stock data", Toast.LENGTH_LONG).show();
        return "";
    }

    public double getChangeDoub(){
        if(mStock != null){
            return mStock.getChangePercent();
        }

        return 0;

    }

    public String getMarketCap(){
        if(mStock != null){
            DecimalFormat df = new DecimalFormat("###,###,###,###");
            return df.format(mStock.getMarketCap());
        }
        return "";
    }

    public String getVolume(){
        if(mStock != null){
            DecimalFormat df = new DecimalFormat("###,###,###,###");
            return df.format(mStock.getVolume());
        }
        return "";
    }

    public String getChangeYtdDollars(){
        if(mStock != null){
            Double change = mStock.getLastPrice().subtract(mStock.getChangeYTD()).doubleValue();
           change = Math.floor(change * 100)/100;
            if(change >= 0){
                return "+" + change;
            }else {
                return  "-" + change;
            }

        }
        return "";
    }

    public String getChangeYtdPercent(){
        if(mStock != null){
            Double change = mStock.getChangePercentYTD();
            change = Math.floor(change * 100)/100;
            if(change >= 0){
                return "(+" + change + "%)";
            }
            else {
                return "(-" + change + "%)";
            }
        }
        return "";
    }

  /*  @Bindable
    public String getHoldingChangePercent(){
        if(mStock != null){
            Math.floor(mStoc)
        }

    }*/

    @Bindable
    public String getHoldingsChangeDollars(){
        if(mStock != null && mTransaction.getPrice() != null){

            BigDecimal purchasePrice = new BigDecimal(0);
            if(!mTransaction.getPrice().isEmpty()){
                purchasePrice = new BigDecimal(mTransaction.getPrice());
            }
            BigDecimal quantity = new BigDecimal(mTransaction.getQuantity());

            BigDecimal fees = new BigDecimal(0);
            if(!mTransaction.getFees().isEmpty()){
                fees = new BigDecimal(mTransaction.getFees());
            }

            BigDecimal price = mStock.getLastPrice();

            BigDecimal change = ((purchasePrice
                    .multiply(quantity))
                    .add(fees))
                    .subtract(
                            (price.multiply(quantity))
                    );
            double changeD = Math.floor(change.doubleValue() * 100)/100;
            DecimalFormat df = new DecimalFormat("###,###,###,##0.00");
            if(changeD < 0){
                changeD *= -1;
                return "+$" + df.format(changeD);
            }else {

                return "-$" + df.format(changeD);
            }
        }
        return "";
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
                mStock = quote;
                return;
            } else {
                Call<Stock> call = markitApi.getQuote(mSymbol);  //ToDo refactor this into a method later for DRY
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
                            mStock = stock;
                            notifyPropertyChanged(BR._all);
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
                        mStock = stock;
                        notifyPropertyChanged(BR._all);
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

//// TODO: 3/28/2017 Add decimal format for current stock price, so it doesn't show 148.5 for currency