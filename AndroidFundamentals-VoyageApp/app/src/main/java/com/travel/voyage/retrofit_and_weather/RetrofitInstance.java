package com.travel.voyage.retrofit_and_weather;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * @author Beti
 */
public class RetrofitInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.openweathermap.org/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
