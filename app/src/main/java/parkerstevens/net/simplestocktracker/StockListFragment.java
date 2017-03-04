package parkerstevens.net.simplestocktracker;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import parkerstevens.net.simplestocktracker.databinding.FragmentStockListBinding;
import parkerstevens.net.simplestocktracker.databinding.ListItemStockBinding;

/**
 * Created by pstev on 3/3/2017.
 */

public class StockListFragment extends Fragment {

    private StocksHelper mStocksHelper;

    public static StockListFragment newInstance() {return new StockListFragment();}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStocksHelper = StocksHelper.get(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentStockListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stock_list, container, false);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(new StockAdapter(mStocksHelper.getStocks()));

        return binding.getRoot();

    }

    private class StockHolder extends RecyclerView.ViewHolder {
        private ListItemStockBinding mBinding;

        public StockHolder(ListItemStockBinding binding) {
            super(binding.getRoot());


            mBinding = binding;
        }

        public void bind(Stock stock){
            mBinding.companyNameText.setText(stock.getName());
            mBinding.executePendingBindings();
        }



    }

    private class StockAdapter extends RecyclerView.Adapter<StockHolder> {
        List<Stock> mStockList;

        public StockAdapter(List<Stock> stockList) {
            mStockList = stockList;
        }

        @Override
        public StockHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemStockBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item_stock, parent, false);

            return new StockHolder(binding);
        }

        @Override
        public void onBindViewHolder(StockHolder holder, int position) {
            Stock stock = mStockList.get(position);
            holder.bind(stock);
        }

        @Override
        public int getItemCount() {
            return mStockList.size();
        }
    }
}
