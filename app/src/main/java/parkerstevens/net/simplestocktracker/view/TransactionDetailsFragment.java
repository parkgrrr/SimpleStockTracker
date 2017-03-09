package parkerstevens.net.simplestocktracker.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import parkerstevens.net.simplestocktracker.R;
import parkerstevens.net.simplestocktracker.databinding.FragmentTransactionDetailBinding;
import parkerstevens.net.simplestocktracker.model.CompanyLookup;
import parkerstevens.net.simplestocktracker.viewmodel.TransactionDetailViewModel;

/**
 * Created by pstev on 3/8/2017.
 */

public class TransactionDetailsFragment extends Fragment {
    private static final String ARG_SYMBOL = "lookupStockSymbol";

    public static TransactionDetailsFragment newInstance(CompanyLookup lookup){
        Bundle args = new Bundle();
        args.putString(ARG_SYMBOL, lookup.getSymbol());
        TransactionDetailsFragment fragment = new TransactionDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        FragmentTransactionDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction_detail, container, false);
        binding.setViewModel(new TransactionDetailViewModel(getArguments().getString(ARG_SYMBOL)));
        return binding.getRoot();
    }
}
