package parkerstevens.net.simplestocktracker.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import parkerstevens.net.simplestocktracker.model.CompanyLookup;

/**
 * Created by pstev on 3/8/2017.
 */

public class LookupItemViewModel extends BaseObservable {
    private CompanyLookup mLookup;
    private String mSymbol;
    private String mCompanyName;
    private String mExchange;



    public CompanyLookup getLookup() {
        return mLookup;
    }

    public void setLookup(CompanyLookup lookup) {
        mLookup = lookup;
        mSymbol = mLookup.getSymbol();
        mCompanyName = mLookup.getName();
        mExchange = mLookup.getExchange();
    }

    @Bindable
    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(String symbol) {
        mSymbol = symbol;
    }

    @Bindable
    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
    }

    @Bindable
    public String getExchange() {
        return mExchange;
    }

    public void setExchange(String exchange) {
        mExchange = exchange;
    }
}
