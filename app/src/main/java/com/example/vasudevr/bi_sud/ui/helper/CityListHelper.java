package com.example.vasudevr.bi_sud.ui.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.example.vasudevr.bi_sud.R;
import com.example.vasudevr.bi_sud.network.ApiClient;
import com.example.vasudevr.bi_sud.network.ApiInterface;
import com.example.vasudevr.bi_sud.network.model.CityList;
import com.example.vasudevr.bi_sud.network.model.StateList;
import com.example.vasudevr.bi_sud.ui.view.LandingActivity;
import com.example.vasudevr.bi_sud.ui.view.fragment.BasicInformationFragment;
import com.example.vasudevr.bi_sud.utils.UiUtils;

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
    private final Context mActivityContext;
    private final ProgressDialog mDialog;
    private ApiInterface mApiInterface;


    public CityListHelper(BasicInformationFragment ctx, Context activityContext) {

        mContext = ctx;
        mActivityContext = activityContext;
        mDialog = new ProgressDialog(activityContext);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void callApi(int stateId) {
        if (UiUtils.checkInternetConnection((Activity) mActivityContext)) {
            mDialog.setTitle(R.string.loading);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setCancelable(false);
            mDialog.show();
            Call call = mApiInterface.getCityList(stateId);
            call.enqueue(new Callback<ArrayList<CityList>>() {
                @Override
                public void onResponse(Call<ArrayList<CityList>> call, Response<ArrayList<CityList>> response) {
                    mDialog.dismiss();
                    ArrayList<CityList> cityList = response.body();
                    mContext.setCityList(cityList);
                }

                @Override
                public void onFailure(Call<ArrayList<CityList>> call, Throwable t) {
                    call.cancel();
                }
            });
        } else {
            UiUtils.showErrorAlert(mActivityContext.getString(R.string.error_dialog_header),
                    mActivityContext.getString(R.string.offline_mode_error), (Activity) mActivityContext, 0);
        }
    }
}
