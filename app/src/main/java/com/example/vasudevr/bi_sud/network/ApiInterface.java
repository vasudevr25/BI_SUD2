package com.example.vasudevr.bi_sud.network;

import com.example.vasudevr.bi_sud.network.model.BranchList;
import com.example.vasudevr.bi_sud.network.model.CityList;
import com.example.vasudevr.bi_sud.network.model.MultipleResource;
import com.example.vasudevr.bi_sud.network.model.ProductList;
import com.example.vasudevr.bi_sud.network.model.StateList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vasudevr on 6/8/2017.
 */

public interface ApiInterface {
    @GET("GetProductDetails")
    Call<ArrayList<ProductList>> getProductList(@Query("CompanyId") int id);

    @GET("GetStateList")
    Call<ArrayList<StateList>> getStateList();

    @GET("GetCityList")
    Call<ArrayList<CityList>> getCityList(@Query("StateId") int id);

    @GET("GetBranchList")
    Call<ArrayList<BranchList>> getBranchList(@Query("CityId") int id);
}
