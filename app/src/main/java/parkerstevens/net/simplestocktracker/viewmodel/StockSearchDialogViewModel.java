package parkerstevens.net.simplestocktracker.viewmodel;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.List;

import parkerstevens.net.simplestocktracker.BR;
import parkerstevens.net.simplestocktracker.data.ApiHelper;
import parkerstevens.net.simplestocktracker.model.CompanyLookup;
import parkerstevens.net.simplestocktracker.view.StockSearchDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pstev on 3/7/2017.
 */

public class StockSearchDialogViewModel extends BaseObservable {
    private final String TAG = "StockSearchViewModel";
    private FragmentManager mFragmentManager;
    private String mSearchInput;
    private ArrayAdapter mLookupAdapter;

    public StockSearchDialogViewModel(FragmentManager fragmentManager, StockSearchDialogFragment.LookupAdapter lookupAdapter) {
        mFragmentManager = fragmentManager;
        mLookupAdapter = lookupAdapter;
    }

    @Bindable
    public String getSearchInput() {
        return mSearchInput;
    }

    public void setSearchInput(String searchInput) {
        mSearchInput = searchInput;
        notifyPropertyChanged(BR.searchInput);
    }

    public void searchOnClick(){
        //StocksHelper.get(get).addTransaction();
        fetchLookup(mSearchInput);
        Log.v(TAG, "Search clicked");


    }

    public void fetchLookup(String input){
        ApiHelper.MarkitOnDemandApiInterface apiInterface = ApiHelper.setupRetrofit();
        Call<List<CompanyLookup>> call = apiInterface.getLookup(input);
        call.enqueue(new Callback<List<CompanyLookup>>() {
            @Override
            public void onResponse(Call<List<CompanyLookup>> call, Response<List<CompanyLookup>> response) {
                if(response.isSuccessful()){
                    List<CompanyLookup> lookup = response.body();
                   // stockAdapter.addStockToList(stock, trans);
                    mLookupAdapter.clear();
                    mLookupAdapter.addAll(lookup);
                    Log.i(TAG, "onresponse exec for " + lookup.get(0).getName());
                }
            }

            @Override
            public void onFailure(Call<List<CompanyLookup>> call, Throwable t) {
                Log.i(TAG, "no companies found");

            }
        });

    }
}
