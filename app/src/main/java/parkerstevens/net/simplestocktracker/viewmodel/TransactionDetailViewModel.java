package parkerstevens.net.simplestocktracker.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.math.BigDecimal;
import java.util.UUID;

import parkerstevens.net.simplestocktracker.BR;
import parkerstevens.net.simplestocktracker.data.StocksHelper;
import parkerstevens.net.simplestocktracker.model.Transaction;

/**
 * Created by pstev on 3/8/2017.
 */

public class TransactionDetailViewModel extends BaseObservable {
    private String mName;
    private String mSymbol;
    private String mQuantity;
    private String mPrice;
    private String mFees;
    private Transaction mTransaction;

    public TransactionDetailViewModel(String symbol, UUID uuid) {
        mSymbol = symbol;
        mTransaction = new Transaction(uuid);
        mTransaction.setSymbol(symbol);
        mTransaction.setPrice(new BigDecimal(0));
        mTransaction.setFees(new BigDecimal(0));
        mTransaction.setQuantity(0);
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

    public Transaction getTransaction() {
        return mTransaction;
    }

    public void addTransaction(StocksHelper stocksHelper){
        stocksHelper.addTransaction(mTransaction);
    }
}
