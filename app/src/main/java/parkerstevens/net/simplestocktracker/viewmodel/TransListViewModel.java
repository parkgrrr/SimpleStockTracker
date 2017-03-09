package parkerstevens.net.simplestocktracker.viewmodel;


import android.databinding.BaseObservable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import parkerstevens.net.simplestocktracker.view.StockSearchDialogFragment;

/**
 * Created by pstev on 3/7/2017.
 */

public class TransListViewModel extends BaseObservable {
    private final String TAG = "TransListViewModel";
    private FragmentManager mFragmentManager;

    public TransListViewModel(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    public void fabOnClick(){
       // FragmentManager fm = new F
        DialogFragment df = new StockSearchDialogFragment();
        df.show(mFragmentManager,"dialog_search");
        Log.v(TAG, "FAB clicked");
    }
}
