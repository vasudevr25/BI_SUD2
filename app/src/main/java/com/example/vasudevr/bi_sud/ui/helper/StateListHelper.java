package com.example.vasudevr.bi_sud.ui.helper;

import android.app.ProgressDialog;

import com.example.vasudevr.bi_sud.network.ApiClient;
import com.example.vasudevr.bi_sud.network.ApiInterface;
import com.example.vasudevr.bi_sud.network.model.StateList;
import com.example.vasudevr.bi_sud.ui.view.LandingActivity;
import com.example.vasudevr.bi_sud.ui.view.fragment.BasicInformationFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vasudevr on 6/15/2017.
 */

public class StateListHelper {

    private static final String TAG = LandingHelper.class.getSimpleName();
    private final BasicInformationFragment mContext;
    private ApiInterface mApiInterface;


    public StateListHelper(BasicInformationFragment ctx) {

        mContext = ctx;
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void callApi() {

        Call call = mApiInterface.getStateList();
        call.enqueue(new Callback<ArrayList<StateList>>() {
            @Override
            public void onResponse(Call<ArrayList<StateList>> call, Response<ArrayList<StateList>> response) {
                ArrayList<StateList> stateList = response.body();
                mContext.setStateList(stateList);
            }

            @Override
            public void onFailure(Call<ArrayList<StateList>> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
