package com.example.vasudevr.bi_sud.ui.helper;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.vasudevr.bi_sud.network.ApiClient;
import com.example.vasudevr.bi_sud.network.ApiInterface;
import com.example.vasudevr.bi_sud.network.model.ProductList;
import com.example.vasudevr.bi_sud.ui.view.LandingActivity;
import com.example.vasudevr.bi_sud.ui.view.MainActivity;
import com.example.vasudevr.bi_sud.ui.view.fragment.LandingFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vasudevr on 6/8/2017.
 */

public class LandingHelper {

    private static final String TAG = LandingHelper.class.getSimpleName();
    private final LandingActivity mActivityContext;
    private final ProgressDialog mDialog;
    private ApiInterface mApiInterface;


    public LandingHelper(LandingActivity ctx) {

        mActivityContext = ctx;
        mDialog = new ProgressDialog(mActivityContext);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void callApi() {

        Call call = mApiInterface.getProductList(1);
        call.enqueue(new Callback<ArrayList<ProductList>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductList>> call, Response<ArrayList<ProductList>> response) {
                ArrayList<ProductList> productList = response.body();
                mActivityContext.setProductDetails(productList);
            }

            @Override
            public void onFailure(Call<ArrayList<ProductList>> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
