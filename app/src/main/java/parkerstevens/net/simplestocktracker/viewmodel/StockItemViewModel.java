package parkerstevens.net.simplestocktracker.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import parkerstevens.net.simplestocktracker.R;
import parkerstevens.net.simplestocktracker.data.StocksHelper;
import parkerstevens.net.simplestocktracker.model.Stock;
import parkerstevens.net.simplestocktracker.model.Transaction;
import parkerstevens.net.simplestocktracker.view.TransactionDetailsFragment;

/**
 * Created by pstev on 3/3/2017.
 */

public class StockItemViewModel extends BaseObservable {
    private StocksHelper mStocksHelper;
    private Stock mStock;
    private Transaction mTrans;
    private FragmentManager mFragmentManager;

    public StockItemViewModel(Context context) {
        mStocksHelper = StocksHelper.get(context);
        mFragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
    }

    public void setStock(Stock stock){
        mStock = stock;
        notifyChange();
    }

    public void setTrans(Transaction trans) {
        mTrans = trans;
    }

    private BigDecimal calcProfits(){

        BigDecimal purchasePrice = new BigDecimal(mTrans.getPrice());
        BigDecimal quantity = new BigDecimal(mTrans.getQuantity());
        BigDecimal fees = new BigDecimal(mTrans.getFees());
        BigDecimal price = mStock.getLastPrice();

        return ((purchasePrice
                .multiply(quantity))
                .add(fees))
                .subtract(
                        (price.multiply(quantity))
                );


    }

    public void onClickTransItem(){

        Fragment transDetail = TransactionDetailsFragment.newInstance(mTrans.getSymbol(), mTrans.getId());

        FragmentTransaction fragTransaction = mFragmentManager.beginTransaction();
// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        fragTransaction.replace(R.id.fragment_container, transDetail, "TransactionDetail");
        fragTransaction.addToBackStack("stockList");
// Commit the transaction
        fragTransaction.commit();

    }

    @Bindable
    public String getName(){ return mStock.getName();}

    @Bindable
    public String getSymbol(){return  mStock.getSymbol();}

    @Bindable
    public String getLastPrice(){
        if (mStock.getLastPrice() == null){
            return "unavailable";
        }
        return "$" + mStock.getLastPrice();
    }

    @Bindable
    public String getChangePercent(){return Math.floor(mStock.getChangePercent() * 100)/100 + "%";}

    @Bindable
    public double getChangePerInt() {return mStock.getChangePercent();}

    @Bindable
    public String getChangeDollars() {
        if (mStock.getChange() == null){
            return "$0.00";
        }
        DecimalFormat df = new DecimalFormat("###,###,###,##0.00");
        return "$"+ df.format(mStock.getChange());
    }

    @Bindable
    public String getPurchasePrice(){return mTrans.getPrice() + "";}

    @Bindable
    public String getSharesAmount(){return mTrans.getQuantity() + "";}

    @Bindable
    public String getProfits() {return calcProfits() + ""; }

}
