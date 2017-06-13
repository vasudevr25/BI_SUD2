package com.example.vasudevr.bi_sud.ui.view;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.vasudevr.bi_sud.R;
import com.example.vasudevr.bi_sud.ui.listener.IMain;
import com.example.vasudevr.bi_sud.ui.view.fragment.BasicInformationFragment;
import com.example.vasudevr.bi_sud.ui.view.fragment.LandingFragment;

/**
 * Created by vasudevr on 6/9/2017.
 */

public class LandingActivity extends AppCompatActivity implements IMain {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String LANDING_FRAGMENT_NAME = "Landing";

    private FragmentManager mFragmentManager;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        mActionBar = getSupportActionBar();
        if (mActionBar != null)
            mActionBar.setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState == null) {
            mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, new LandingFragment())
                    .commitNow();
        }
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
        Fragment fragment = null;
        if (fragmentName.equalsIgnoreCase(LANDING_FRAGMENT_NAME)) {
            fragment = new LandingFragment();
            if (fragment != null) {
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
        } else {
            finish();
        }
    }
}
