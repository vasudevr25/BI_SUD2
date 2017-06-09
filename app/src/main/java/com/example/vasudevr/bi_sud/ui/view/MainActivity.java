package com.example.vasudevr.bi_sud.ui.view;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vasudevr.bi_sud.R;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ExpandableRelativeLayout mExpandableBasicInformationLayout, mExpandableProductInformationLayout,
    mExpandableRidersLayout, mExpandableSummaryLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void basicInformationButton(View view) {
        mExpandableBasicInformationLayout = (ExpandableRelativeLayout) findViewById(R.id.expandableBasicInformationLayout);
        mExpandableBasicInformationLayout.toggle(); // toggle expand and collapse
    }

    public void productInformationButton(View view) {
        mExpandableProductInformationLayout = (ExpandableRelativeLayout) findViewById(R.id.expandableProductInformationLayout);
        mExpandableProductInformationLayout.toggle(); // toggle expand and collapse
    }

    public void ridersButton(View view) {
        mExpandableRidersLayout = (ExpandableRelativeLayout) findViewById(R.id.expandableRidersLayout);
        if(mExpandableRidersLayout.isExpanded()) {
            mExpandableRidersLayout.collapse();
        } else {
            mExpandableRidersLayout.expand();
        }
    }

    public void summaryButton(View view) {
        mExpandableSummaryLayout = (ExpandableRelativeLayout) findViewById(R.id.expandableSummaryLayout);
        mExpandableSummaryLayout.toggle(); // toggle expand and collapse
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
