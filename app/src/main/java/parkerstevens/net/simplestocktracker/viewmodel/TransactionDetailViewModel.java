package parkerstevens.net.simplestocktracker.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by pstev on 3/8/2017.
 */

public class TransactionDetailViewModel extends BaseObservable {
    private String mSymbol;

    public TransactionDetailViewModel(String symbol) {
        mSymbol = symbol;
    }

    @Bindable
    public String getSymbol() {
        return mSymbol;
    }
}
