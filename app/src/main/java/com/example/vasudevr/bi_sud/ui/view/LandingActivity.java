package com.example.vasudevr.bi_sud.ui.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.vasudevr.bi_sud.R;
import com.example.vasudevr.bi_sud.network.model.ProductList;
import com.example.vasudevr.bi_sud.network.model.StateList;
import com.example.vasudevr.bi_sud.ui.helper.LandingHelper;
import com.example.vasudevr.bi_sud.ui.helper.StateListHelper;
import com.example.vasudevr.bi_sud.ui.listener.IMain;
import com.example.vasudevr.bi_sud.ui.view.fragment.BasicInformationFragment;
import com.example.vasudevr.bi_sud.ui.view.fragment.LandingFragment;
import com.example.vasudevr.bi_sud.ui.view.fragment.ProductInformationFragment;
import com.example.vasudevr.bi_sud.ui.listener.OnUpdateListListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by vasudevr on 6/9/2017.
 */

public class LandingActivity extends AppCompatActivity implements IMain {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String LANDING_FRAGMENT_NAME = "Landing";

    private FragmentManager mFragmentManager;
    private ActionBar mActionBar;
    private ArrayList<ProductList> mProductArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        callApi();
        mActionBar = getSupportActionBar();
        if (mActionBar != null)
            mActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Log.d(TAG, "back button pressed :");
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void updateFragment(String fragmentName) {
        Fragment fragment;
        if (fragmentName.equalsIgnoreCase(LANDING_FRAGMENT_NAME)) {
            fragment = new LandingFragment();
            if (fragment != null) {
                Bundle data = new Bundle();
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<ProductList>>() {}.getType();
                String productDataString = gson.toJson(mProductArrayList, type);
                data.putString("PRODUCT_KEY", productDataString);
                fragment.setArguments(data);
                mFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commitNow();
            }
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        Log.d(TAG, "inside onBackPressed fragment :" + fragment);
        if (fragment instanceof BasicInformationFragment) {
            updateFragment(LANDING_FRAGMENT_NAME);
        } else if (fragment instanceof ProductInformationFragment) {
            updateFragment(LANDING_FRAGMENT_NAME);
        } else {
            finish();
        }
    }


    public void callApi() {
        LandingHelper helper = new LandingHelper(this);
        helper.callApi();
    }

    public void setProductDetails(ArrayList<ProductList> arrayList) {
        mProductArrayList = arrayList;
        mFragmentManager = getSupportFragmentManager();
        LandingFragment fragment = new LandingFragment();
        Bundle data = new Bundle();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ProductList>>() {}.getType();
        String productDataString = gson.toJson(mProductArrayList, type);
        data.putString("PRODUCT_KEY", productDataString);
        fragment.setArguments(data);
        mFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commitNow();
    }
}
