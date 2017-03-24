package parkerstevens.net.simplestocktracker.viewmodel;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.List;

import parkerstevens.net.simplestocktracker.BR;
import parkerstevens.net.simplestocktracker.data.ApiHelper;
import parkerstevens.net.simplestocktracker.model.CompanyLookup;
import parkerstevens.net.simplestocktracker.view.LookupDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pstev on 3/7/2017.
 */

public class LookupDialogViewModel extends BaseObservable {
    private final String TAG = "StockSearchViewModel";
    private String mSearchInput;
    private ArrayAdapter mLookupAdapter;
    private Boolean isLoading = false;

    public LookupDialogViewModel(LookupDialogFragment.LookupAdapter lookupAdapter) {
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

    @Bindable
    public Boolean getLoading() {
        return isLoading;
    }

    public void setLoading(Boolean loading) {
        isLoading = loading;
        notifyPropertyChanged(BR.loading);
    }

    public void searchOnClick(){
        //StocksHelper.get(get).addTransaction();
        setLoading(true);
        fetchLookup(mSearchInput);
        Log.v(TAG, "Search clicked");


    }

    public void fetchLookup(final String input){
        ApiHelper.MarkitOnDemandApiInterface apiInterface = ApiHelper.setupRetrofit();
        Call<List<CompanyLookup>> call = apiInterface.getLookup(input);
        call.enqueue(new Callback<List<CompanyLookup>>() {
            @Override
            public void onResponse(Call<List<CompanyLookup>> call, Response<List<CompanyLookup>> response) {
                setLoading(false);
                if(response.isSuccessful()){
                    List<CompanyLookup> lookup = response.body();
                   // stockAdapter.addStockToList(stock, trans);
                    mLookupAdapter.clear();
                    if(lookup.size() > 0){
                        mLookupAdapter.addAll(lookup);
                    }
                    else {
                        CompanyLookup lookup0 = new CompanyLookup();
                        lookup0.setName("No companies found");
                        mLookupAdapter.add(lookup0);
                    }


                    Log.i(TAG, "onresponse exec for " + input);
                }
            }

            @Override
            public void onFailure(Call<List<CompanyLookup>> call, Throwable t) {
                setLoading(false);
                Log.i(TAG, "no companies found");

                CompanyLookup lookup = new CompanyLookup();
                lookup.setName("No companies found or bad connection");
                mLookupAdapter.add(lookup);

            }
        });

    }
}
