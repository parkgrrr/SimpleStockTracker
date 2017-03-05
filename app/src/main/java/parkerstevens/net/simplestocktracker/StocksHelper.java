package parkerstevens.net.simplestocktracker;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pstev on 3/3/2017.
 */

public class StocksHelper{
    private static final String TAG = "StocksHelper";
    public static final String BASE_URL = "http://dev.markitondemand.com/Api/v2/";
    private static StocksHelper mStocksHelper;
    private Context mContext;
    private Retrofit mRetrofit;
    private Gson mGson;
    private MarkitOnDemandApiInterface mMarkitApi;
    private List<Stock> mStocks = new ArrayList<>();


    public static StocksHelper get(Context context){
        if(mStocksHelper == null){
            mStocksHelper = new StocksHelper(context);
        }
            return mStocksHelper;
    }

    private StocksHelper(Context context){
        mContext = context.getApplicationContext();
        setupRetrofit();
    }

    public MarkitOnDemandApiInterface getMarkitApi() {
        return mMarkitApi;
    }

    public List<Stock> getStocks() {
        return mStocks;
    }

    public void fetchStocks(final StockListFragment.StockAdapter stockAdapter){
        List<String> quotes = new ArrayList<>();


        for(int i = 0; i < 5; i++){

            quotes.add("MSFT");
        }

        for (String quote:
             quotes) {
            Call<Stock> call = mMarkitApi.getQuote(quote);
            call.enqueue(new Callback<Stock>() {
                @Override
                public void onResponse(Call<Stock> call, Response<Stock> response) {
                    if(response.isSuccessful()){
                        Stock stock = response.body();
                        stockAdapter.addStockToList(stock);
                        Log.i(TAG, "onresponse exec for " + stock.getName());
                    }

                }

                @Override
                public void onFailure(Call<Stock> call, Throwable t) {
                    Log.e(TAG, "Failed API call", t);

                }
            });

        }

    }

    private void setupRetrofit(){
        mGson = new GsonBuilder()
                .setLenient()
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();

        mMarkitApi = mRetrofit.create(MarkitOnDemandApiInterface.class);
    }
}
