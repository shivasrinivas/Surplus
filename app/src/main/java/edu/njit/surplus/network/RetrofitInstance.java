package edu.njit.surplus.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by srini on 11/11/17.
 */

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://10.200.184.81:8080/Surplus/rest/services/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
