package com.example.vasudevr.bi_sud.ui.helper;

import android.app.ProgressDialog;

import com.example.vasudevr.bi_sud.network.ApiClient;
import com.example.vasudevr.bi_sud.network.ApiInterface;
import com.example.vasudevr.bi_sud.ui.view.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vasudevr on 6/8/2017.
 */

public class MainHelper {

    private static final String TAG = MainHelper.class.getSimpleName();
    private final MainActivity mActivityContext;
    private final ProgressDialog mDialog;
    private ApiInterface mApiInterface;


    public MainHelper(MainActivity ctx) {

        mActivityContext = ctx;
        mDialog = new ProgressDialog(mActivityContext);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void callApi() {

        Call call = mApiInterface.doGetListResources();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });
    }
}
