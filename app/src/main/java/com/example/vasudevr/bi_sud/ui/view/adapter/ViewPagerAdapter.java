package com.example.vasudevr.bi_sud.ui.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vasudevr.bi_sud.R;

import java.util.Random;

/**
 * Created by vasudevr on 6/12/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private final Random random = new Random();
    private int mSize;
    private Context mContext;

    // Keep all Images in array
    public int[] mThumbIds = {
            R.mipmap.facebook, R.mipmap.whatsapp,
            R.mipmap.instagram, R.mipmap.you_tube,
            R.mipmap.google_maps, R.mipmap.google_plus,
            R.mipmap.twitter, R.mipmap.pinterest,
    };

    public ViewPagerAdapter(Context context) {
        mContext = context;
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
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.product_indictor_layout, parent, false);
        ImageView productImage = (ImageView) layout.findViewById(R.id.product_image_view);
        TextView productName = (TextView) layout.findViewById(R.id.product_name);
        productImage.setImageResource(mThumbIds[position]);
        productName.setText("PRODUCT NAME");
        parent.addView(layout);
        return layout;
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
