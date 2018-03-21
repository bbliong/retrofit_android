package com.example.user.ketegori_2.api;

import com.example.user.ketegori_2.model.Scholarship;
import com.example.user.ketegori_2.model.ScholarshipsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by user on 17/3/2018.
 */

public interface Service {
    @GET("kategori/{kategori}")
    Call<List<ScholarshipsResponse>> getData(@Path("kategori") String kategori);
}
