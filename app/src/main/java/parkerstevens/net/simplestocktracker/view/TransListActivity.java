package parkerstevens.net.simplestocktracker.view;

import android.support.v4.app.Fragment;

public class TransListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return TransListFragment.newInstance();
    }

}
