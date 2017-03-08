package parkerstevens.net.simplestocktracker;

import android.support.v4.app.Fragment;

public class TransListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return TransListFragment.newInstance();
    }

}
