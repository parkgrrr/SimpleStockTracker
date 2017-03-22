package parkerstevens.net.simplestocktracker.data;

import android.os.SystemClock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import parkerstevens.net.simplestocktracker.model.CompanyLookup;
import parkerstevens.net.simplestocktracker.model.Stock;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pstev on 3/4/2017.
 */

public class ApiHelper {

    private static final String BASE_URL = "http://dev.markitondemand.com/MODApis/Api/v2/";

    public static MarkitOnDemandApiInterface setupRetrofit(){
        OkHttpClient client = setupLogging();
        Gson gson = new GsonBuilder()
                //.setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit.create(MarkitOnDemandApiInterface.class);

    }

    private static OkHttpClient setupLogging(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    // Can be Level.BASIC, Level.HEADERS, or Level.BODY
    // See http://square.github.io/okhttp/3.x/logging-interceptor/ to see the options.
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.networkInterceptors().add(httpLoggingInterceptor);

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);
        //dispatcher.setMaxRequestsPerHost(1);
        //dispatcher.executorService().awaitTermination(1000, TimeUnit.MILLISECONDS);

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                SystemClock.sleep(500);
                return chain.proceed(chain.request());
            }
        };
        builder.addNetworkInterceptor(interceptor);
        builder.dispatcher(dispatcher);
        return builder.build();
    }

    public interface MarkitOnDemandApiInterface {

        @GET("Quote/json")
        Call<Stock> getQuote(@Query("symbol")String symbol);

        @GET("Lookup/json")
        Call<List<CompanyLookup>> getLookup(@Query("input") String input);

    }
}


