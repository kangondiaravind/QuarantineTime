package com.aravind.coronatracker;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/dev/items/count/{pincode}")
    Call<DieaseDetail> getDieaseDetails(@Path("pincode") String pincode);
}
