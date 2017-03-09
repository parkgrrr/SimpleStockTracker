package parkerstevens.net.simplestocktracker.view;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import parkerstevens.net.simplestocktracker.R;
import parkerstevens.net.simplestocktracker.databinding.DialogStockSearchBinding;
import parkerstevens.net.simplestocktracker.databinding.ListItemLookupBinding;
import parkerstevens.net.simplestocktracker.model.CompanyLookup;
import parkerstevens.net.simplestocktracker.viewmodel.LookupItemViewModel;
import parkerstevens.net.simplestocktracker.viewmodel.StockSearchDialogViewModel;

/**
 * Created by pstev on 3/7/2017.
 */

public class StockSearchDialogFragment extends AppCompatDialogFragment {
    ArrayList<CompanyLookup> mLookups = new ArrayList<>();
    LookupAdapter mLookupAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mLookupAdapter = new LookupAdapter(getContext(),R.layout.list_item_lookup, mLookups);

        DialogStockSearchBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_stock_search, null, false);
        binding.setViewModel(
                new StockSearchDialogViewModel(getFragmentManager(), mLookupAdapter)
        );
        binding.searchDialogListView.setAdapter(mLookupAdapter);
        return new AlertDialog.Builder(getActivity())
                .setView(binding.getRoot())
                .setTitle("Search for a Company")
                .create();
    }

    public class LookupAdapter extends ArrayAdapter<CompanyLookup> {


        public LookupAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<CompanyLookup> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            CompanyLookup lookup = getItem(position);
            ListItemLookupBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_lookup, parent, false);
            itemBinding.setViewModel(new LookupItemViewModel(getFragmentManager()));
            itemBinding.getViewModel().setLookup(lookup);

            return itemBinding.getRoot();
        }

        @Override
        public void addAll(CompanyLookup... items) {
            super.addAll(items);
            notifyDataSetChanged();
        }
    }

    // TODO: 3/8/2017 Add a spinner or indicator that api is querying stock
}
