package parkerstevens.net.simplestocktracker.view;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

import parkerstevens.net.simplestocktracker.R;
import parkerstevens.net.simplestocktracker.data.StocksHelper;
import parkerstevens.net.simplestocktracker.databinding.FragmentStockListBinding;
import parkerstevens.net.simplestocktracker.databinding.ListItemStockBinding;
import parkerstevens.net.simplestocktracker.model.Stock;
import parkerstevens.net.simplestocktracker.model.Transaction;
import parkerstevens.net.simplestocktracker.viewmodel.StockItemViewModel;
import parkerstevens.net.simplestocktracker.viewmodel.TransListViewModel;

/**
 * Created by pstev on 3/3/2017.
 */

public class TransListFragment extends Fragment {

    private StocksHelper mStocksHelper;
    private StockAdapter mStockAdapter;
    private TransListViewModel mTransListViewModel;

    public static TransListFragment newInstance() {return new TransListFragment();}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStocksHelper = StocksHelper.get(getActivity());
        mTransListViewModel = new TransListViewModel(getFragmentManager(), getContext());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mStockAdapter == null){
            mStockAdapter = new StockAdapter(mStocksHelper.getTransactions());
        }

        FragmentStockListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stock_list, container, false);
        binding.setViewModel(mTransListViewModel);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(mStockAdapter);
        binding.toolbar.inflateMenu(R.menu.fragment_stock_list_menu);
        binding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.refresh_button:
                        refreshStocks(mTransListViewModel);
                        return true;
                    default:
                        return false;
                }
            }
        });


        mTransListViewModel.createItemTouchHelper(mStockAdapter)
                .attachToRecyclerView(binding.recyclerView);

        return binding.getRoot();

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshStocks(mTransListViewModel);
    }

    public void refreshStocks(TransListViewModel viewModel){
        mStockAdapter.mTransList.clear();
        viewModel.fetchStocks(mStockAdapter);
    }


    public class StockHolder extends RecyclerView.ViewHolder {
        private ListItemStockBinding mBinding;

        public StockHolder(ListItemStockBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel(new StockItemViewModel(getContext()));
        }

        public void bind(Transaction trans, Stock stock){
            mBinding.getViewModel().setTrans(trans);
            mBinding.getViewModel().setStock(stock);
            mBinding.executePendingBindings();
        }

        public void bind(Transaction trans){
            mBinding.getViewModel().setTrans(trans);
        }



    }

    public class StockAdapter extends RecyclerView.Adapter<StockHolder> {
        List<Transaction> mTransList;
        HashMap<String,Stock> mStockHash = new HashMap<>();

        public StockAdapter(List<Transaction> transList) {
            mTransList = transList;
        }

        @Override
        public StockHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemStockBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item_stock, parent, false);


            return new StockHolder(binding);
        }

        @Override
        public void onBindViewHolder(StockHolder holder, int position) {
            Transaction trans = mTransList.get(position);
            if(!mStockHash.isEmpty())
            {
                Stock stock = mStockHash.get(trans.getSymbol());
                holder.bind(trans, stock);
                return;
            }

            //String symbol = trans.getSymbol();
           // mStocksHelper.fetchStock(this, trans);
            holder.bind(trans);
        }

        @Override
        public int getItemCount() {
            return mTransList.size();
        }

        public void addStockToList(Stock stock, Transaction trans){
           // mStockList.add(stock);
            mStockHash.put(stock.getSymbol(), stock);
            mTransList.add(trans);
            this.notifyDataSetChanged();
        }

        public void onItemDismiss(int position) {
            Transaction t = mTransList.get(position);
            mTransList.remove(position);
            notifyItemRemoved(position);
            mStocksHelper.deleteTransaction(t.getId());
            showDeleteSnackbar(t);
        }

        public void showDeleteSnackbar(final Transaction t){
            Resources res = getResources();
            String message = res.getString(R.string.delete_message, t.getSymbol());

            Snackbar mySnackbar = Snackbar.make(getView().findViewById(R.id.coord_layout),
                    message, Snackbar.LENGTH_LONG);
            mySnackbar.setAction(R.string.undo, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mStocksHelper.addTransaction(t);
                    refreshStocks(mTransListViewModel);
                }
            });
            mySnackbar.show();

        }
    }


}
