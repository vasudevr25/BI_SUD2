package com.example.vasudevr.bi_sud.ui.helper;

import com.example.vasudevr.bi_sud.network.ApiClient;
import com.example.vasudevr.bi_sud.network.ApiInterface;
import com.example.vasudevr.bi_sud.network.model.CityList;
import com.example.vasudevr.bi_sud.network.model.StateList;
import com.example.vasudevr.bi_sud.ui.view.fragment.BasicInformationFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vasudevr on 6/15/2017.
 */

public class CityListHelper {

    private static final String TAG = LandingHelper.class.getSimpleName();
    private final BasicInformationFragment mContext;
    private ApiInterface mApiInterface;


    public CityListHelper(BasicInformationFragment ctx) {

        mContext = ctx;
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void callApi(int stateId) {

        Call call = mApiInterface.getCityList(stateId);
        call.enqueue(new Callback<ArrayList<CityList>>() {
            @Override
            public void onResponse(Call<ArrayList<CityList>> call, Response<ArrayList<CityList>> response) {
                ArrayList<CityList> cityList = response.body();
                mContext.setCityList(cityList);
            }

            @Override
            public void onFailure(Call<ArrayList<CityList>> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
