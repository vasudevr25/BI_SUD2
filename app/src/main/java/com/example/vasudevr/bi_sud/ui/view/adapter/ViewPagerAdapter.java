package com.example.vasudevr.bi_sud.ui.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vasudevr.bi_sud.R;
import com.example.vasudevr.bi_sud.ui.view.fragment.BasicInformationFragment;
import com.example.vasudevr.bi_sud.ui.view.fragment.LandingFragment;

import java.util.Random;

/**
 * Created by vasudevr on 6/12/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private int mSize;
    private Context mContext;
    private LandingFragment mFragment;

    // Keep all Images in array
    public int[] mThumbIds = {
            R.mipmap.facebook, R.mipmap.whatsapp,
            R.mipmap.instagram, R.mipmap.you_tube,
            R.mipmap.google_maps, R.mipmap.google_plus,
            R.mipmap.twitter, R.mipmap.pinterest,
    };

    public ViewPagerAdapter(Context context, LandingFragment fragment) {
        mContext = context;
        mFragment = fragment;
    }

   /* public ViewPagerAdapter(int count) {
        mSize = count;
    }*/

    @Override public int getCount() {
        return mThumbIds.length;
    }

    @Override public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView((View) object);
    }

    @Override public Object instantiateItem(ViewGroup parent, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.product_indictor_layout, parent, false);
        CardView productCardView = (CardView) view.findViewById(R.id.card_view);
        ImageView productImage = (ImageView) view.findViewById(R.id.product_image_view);
        TextView productName = (TextView) view.findViewById(R.id.product_name);
        productImage.setImageResource(mThumbIds[position]);
        productName.setText("PRODUCT NAME");
        parent.addView(view);
        productCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = mFragment.getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new BasicInformationFragment())
                        .commitNow();
            }
        });
        return view;
    }

    public void addItem() {
        mSize++;
        notifyDataSetChanged();
    }

    public void removeItem() {
        mSize--;
        mSize = mSize < 0 ? 0 : mSize;

        notifyDataSetChanged();
    }
}
