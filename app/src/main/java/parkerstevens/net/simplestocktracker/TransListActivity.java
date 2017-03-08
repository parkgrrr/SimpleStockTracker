package parkerstevens.net.simplestocktracker;

import android.support.v4.app.Fragment;

public class StockListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return StockListFragment.newInstance();
    }

}
