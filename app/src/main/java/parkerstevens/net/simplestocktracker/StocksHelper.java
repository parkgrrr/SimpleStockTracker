package parkerstevens.net.simplestocktracker;

import android.content.Context;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pstev on 3/3/2017.
 */

public class StocksHelper {
    private static StocksHelper mStocksHelper;
    private Context mContext;

    public static StocksHelper get(Context context){
        if(mStocksHelper == null){
            mStocksHelper = new StocksHelper(context);
        }
            return mStocksHelper;
    }

    private StocksHelper(Context context){
        mContext = context.getApplicationContext();
    }

    public List<Stock> getStocks(){
        List<Stock> stocks = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            Stock stocky = new Stock();
            stocky.setName("NAME" + i);
            stocky.setSymbol("SYMBOL" + i);
            stocky.setLastPrice(new BigDecimal(50 + i));
            stocks.add(i, stocky);
        }
        return stocks;

    }
}
