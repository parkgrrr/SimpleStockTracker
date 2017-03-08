package parkerstevens.net.simplestocktracker;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by pstev on 3/3/2017.
 */

public class StockViewModel extends BaseObservable {
    private StocksHelper mStocksHelper;
    private Stock mStock;
    private Transaction mTrans;

    public StockViewModel(Context context) {
        mStocksHelper = StocksHelper.get(context);
    }

    public void setStock(Stock stock){
        mStock = stock;
        notifyChange();
    }

    public void setTrans(Transaction trans) {
        mTrans = trans;
    }

    @Bindable
    public String getName(){ return mStock.getName();}

    @Bindable
    public String getSymbol(){return  mStock.getSymbol();}

    @Bindable
    public String getLastPrice(){return mStock.getLastPrice() + "";}


}
