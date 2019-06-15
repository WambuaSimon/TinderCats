package com.simon.cattinder.network;

import com.simon.cattinder.model.CatModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("search")
    Call<List<CatModel>> getCatModel();

}
