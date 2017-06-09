package com.example.vasudevr.bi_sud.ui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.vasudevr.bi_sud.R;
import com.example.vasudevr.bi_sud.ui.view.adapter.GridImageAdapter;

/**
 * Created by vasudevr on 6/9/2017.
 */

public class GridActivity extends AppCompatActivity {

    // Keep all Images in array
    public int[] mThumbIds = {
            R.mipmap.facebook, R.mipmap.whatsapp,
            R.mipmap.instagram, R.mipmap.you_tube,
            R.mipmap.google_maps, R.mipmap.google_plus,
            R.mipmap.twitter, R.mipmap.pinterest,
    };

    private GridView mGridView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        GridImageAdapter adapter = new GridImageAdapter(GridActivity.this, mThumbIds);
        mGridView=(GridView)findViewById(R.id.gridView);
        mGridView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
