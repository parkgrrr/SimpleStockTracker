package parkerstevens.net.simplestocktracker.viewmodel;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import parkerstevens.net.simplestocktracker.BR;
import parkerstevens.net.simplestocktracker.view.StockSearchDialogFragment;

/**
 * Created by pstev on 3/7/2017.
 */

public class TransListViewModel extends BaseObservable {
    private final String TAG = "TransListViewModel";
    private FragmentManager mFragmentManager;
    private String mSearchInput;

    public TransListViewModel(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    @Bindable
    public String getSearchInput() {
        return mSearchInput;
    }

    public void setSearchInput(String searchInput) {
        mSearchInput = searchInput;
        notifyPropertyChanged(BR.searchInput);
    }

    public void fabOnClick(){
       // FragmentManager fm = new F
        DialogFragment df = new StockSearchDialogFragment();
        df.show(mFragmentManager,TAG);
        Log.v(TAG, "FAB clicked");
    }

    public void searchOnClick(){
        //StocksHelper.get(get).addTransaction();
        Log.v(TAG, "Search clicked");
    }
}
