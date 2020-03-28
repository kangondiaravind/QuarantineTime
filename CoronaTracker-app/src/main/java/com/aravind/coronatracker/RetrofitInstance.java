package com.aravind.coronatracker;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    //https://mcbc3n72td.execute-api.ap-south-1.amazonaws.com/dev/items/count/561211

    private static final String BASE_URL = "https://mcbc3n72td.execute-api.ap-south-1.amazonaws.com";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
