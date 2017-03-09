package parkerstevens.net.simplestocktracker.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import parkerstevens.net.simplestocktracker.R;
import parkerstevens.net.simplestocktracker.model.CompanyLookup;
import parkerstevens.net.simplestocktracker.view.TransactionDetailsFragment;

/**
 * Created by pstev on 3/8/2017.
 */

public class LookupItemViewModel extends BaseObservable {
    private CompanyLookup mLookup;
    private String mSymbol;
    private String mCompanyName;
    private String mExchange;
    private FragmentManager mFragmentManager;

    public LookupItemViewModel(FragmentManager fm) {
        mFragmentManager = fm;
    }

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

    public void onCompanyClick() {
        Fragment transDetail = TransactionDetailsFragment.newInstance(mLookup);

        FragmentTransaction fragTransaction = mFragmentManager.beginTransaction();
// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        fragTransaction.replace(R.id.fragment_container, transDetail, "TransactionDetail");
       fragTransaction.addToBackStack("stockList");
// Commit the transaction
        fragTransaction.commit();

        DialogFragment mDialog = (DialogFragment) mFragmentManager.findFragmentByTag("dialog_search");
        mDialog.dismiss();

        Log.v("LookupItemViewModel", mCompanyName + " clicked");

    }
}
