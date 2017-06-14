package com.example.vasudevr.bi_sud.network;

import com.example.vasudevr.bi_sud.network.model.MultipleResource;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vasudevr on 6/8/2017.
 */

public interface ApiInterface {
    @GET("getproductdetails")
    Call<MultipleResource> doGetListResources();
}
