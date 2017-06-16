package com.example.vasudevr.bi_sud.ui.view.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vasudevr.bi_sud.R;
import com.example.vasudevr.bi_sud.network.model.ProductList;
import com.example.vasudevr.bi_sud.ui.view.fragment.BasicInformationFragment;
import com.example.vasudevr.bi_sud.ui.view.fragment.LandingFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by vasudevr on 6/13/2017.
 */

public class RecyclerHorizontalViewAdapter extends RecyclerView.Adapter<RecyclerHorizontalViewAdapter.MyViewHolder> implements Filterable {

    private Context mContext;
    private LandingFragment mFragment;
    private ArrayList<ProductList> productArrayList;
    private String mImageURL;
    private static final String IMAGE_URL = "http://bitoolweb.beta.nvest.in/images/ProductImages/";

    // Keep all Images in array
    public int[] mThumbIds = {
            R.mipmap.facebook, R.mipmap.whatsapp,
            R.mipmap.instagram, R.mipmap.you_tube,
            R.mipmap.google_maps, R.mipmap.google_plus,
            R.mipmap.twitter, R.mipmap.pinterest
    };

    @Override
    public Filter getFilter() {
        return null;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout productLayout;
        public ImageView productImage;
        public TextView productName;
        public TextView productDescription;


        public MyViewHolder(View view) {
            super(view);
            productLayout = (LinearLayout) view.findViewById(R.id.product_layout);
            productImage = (ImageView) view.findViewById(R.id.product_image_view);
            productName = (TextView) view.findViewById(R.id.product_name);
            productDescription = (TextView) view.findViewById(R.id.product_description);
        }
    }

    public RecyclerHorizontalViewAdapter(Context context, LandingFragment fragment, ArrayList<ProductList> arrayList) {
        this.mContext = context;
        this.mFragment = fragment;
        this.productArrayList = arrayList;
    }

    @Override
    public RecyclerHorizontalViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_horizontal_list_layout, parent, false);
        return new RecyclerHorizontalViewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerHorizontalViewAdapter.MyViewHolder holder, int position) {
        Log.d("RecyclerHorizontal", "Position::::" + position);
        Picasso.with(mContext).load(IMAGE_URL + productArrayList.get(position).getImageUrl()).into(holder.productImage);
        holder.productDescription.setText(productArrayList.get(position).getDescription());
        holder.productName.setText(productArrayList.get(position).getProductName());
        holder.productLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = mFragment.getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new BasicInformationFragment(mContext))
                        .commitNow();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

}
