package com.example.har.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retroserver {
    private static final String baseURL = "http://10.0.2.2/HAR/";
    private static Retrofit retro;

    public static Retrofit connectRetrofit(){
        if(retro == null){
             retro = new Retrofit.Builder()
                     .baseUrl(baseURL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
        }
        return retro;
    }
}
