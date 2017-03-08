package parkerstevens.net.simplestocktracker.view;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;

import parkerstevens.net.simplestocktracker.R;
import parkerstevens.net.simplestocktracker.databinding.DialogStockSearchBinding;
import parkerstevens.net.simplestocktracker.viewmodel.TransListViewModel;

/**
 * Created by pstev on 3/7/2017.
 */

public class StockSearchDialogFragment extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DialogStockSearchBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_stock_search, null, false);
        binding.setViewModel(new TransListViewModel(getFragmentManager()));
        return new AlertDialog.Builder(getActivity())
                .setView(binding.getRoot())
                .setTitle("Search for a stock")
                .create();
    }
}
