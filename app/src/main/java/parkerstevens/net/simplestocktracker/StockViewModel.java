package parkerstevens.net.simplestocktracker;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by pstev on 3/3/2017.
 */

public class StockViewModel extends BaseObservable {
    private StocksHelper mStocksHelper;
    private Stock mStock;

    public StockViewModel(StocksHelper stocksHelper) {
        mStocksHelper = stocksHelper;
    }

    public void setStock(Stock stock){
        mStock = stock;
        notifyChange();
    }

    @Bindable
    public String getName(){ return mStock.getName();}

    @Bindable
    public String getSymbol(){return  mStock.getSymbol();}

    @Bindable
    public String getLastPrice(){return mStock.getLastPrice() + "";}
}
