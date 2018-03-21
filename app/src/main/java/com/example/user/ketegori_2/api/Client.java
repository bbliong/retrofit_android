package com.example.user.ketegori_2.api;

import com.example.user.ketegori_2.model.ScholarshipsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by user on 17/3/2018.
 */

public class Client {
    public static final String BASE_URL= "http://ayobeasiswa.me/api/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
