package parkerstevens.net.simplestocktracker.data;

import parkerstevens.net.simplestocktracker.model.CompanyLookup;
import parkerstevens.net.simplestocktracker.model.Stock;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pstev on 3/4/2017.
 */

public interface MarkitOnDemandApiInterface {

    @GET("Quote/json")
    Call<Stock> getQuote(@Query("symbol")String symbol);

    @GET("Lookup/json")
    Call<CompanyLookup> getLookup(@Query("input") String input);

}
