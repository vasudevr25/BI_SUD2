package com.example.vasudevr.bi_sud.ui.helper;

import com.example.vasudevr.bi_sud.network.ApiClient;
import com.example.vasudevr.bi_sud.network.ApiInterface;
import com.example.vasudevr.bi_sud.network.model.BranchList;
import com.example.vasudevr.bi_sud.network.model.CityList;
import com.example.vasudevr.bi_sud.ui.view.fragment.BasicInformationFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vasudevr on 6/15/2017.
 */

public class BranchListHelper {

    private static final String TAG = LandingHelper.class.getSimpleName();
    private final BasicInformationFragment mContext;
    private ApiInterface mApiInterface;


    public BranchListHelper(BasicInformationFragment ctx) {

        mContext = ctx;
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void callApi(int cityId) {

        Call call = mApiInterface.getBranchList(cityId);
        call.enqueue(new Callback<ArrayList<BranchList>>() {
            @Override
            public void onResponse(Call<ArrayList<BranchList>> call, Response<ArrayList<BranchList>> response) {
                ArrayList<BranchList> branchList = response.body();
                mContext.setBranchList(branchList);
            }

            @Override
            public void onFailure(Call<ArrayList<BranchList>> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
