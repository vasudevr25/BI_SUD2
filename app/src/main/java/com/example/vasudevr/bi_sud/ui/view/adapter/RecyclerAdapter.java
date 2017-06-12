package com.example.vasudevr.bi_sud.ui.view.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.vasudevr.bi_sud.R;
import com.example.vasudevr.bi_sud.custom.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import static com.example.vasudevr.bi_sud.R.id.collapseActionView;
import static com.example.vasudevr.bi_sud.R.id.design_menu_item_text;
import static com.example.vasudevr.bi_sud.R.id.viewpager;

/**
 * Created by vasudevr on 6/9/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> implements Filterable {

    private Context mContext;
    //ValueFilter mValueFilter;

    @Override
    public Filter getFilter() {
        return null;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ViewPager mViewPager;
        public CircleIndicator mCircleIndicator;
        public TextView mTextView;


        public MyViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text_category);
            mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
            mCircleIndicator = (CircleIndicator) view.findViewById(R.id.indicator);
        }
    }

    public RecyclerAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int categoryPosition = position + 1;
        holder.mTextView.setText(mContext.getString(R.string.text_category) + " " + categoryPosition);
        holder.mViewPager.setAdapter(new ViewPagerAdapter(mContext));
        holder.mCircleIndicator.setViewPager(holder.mViewPager);
        holder.mViewPager.setCurrentItem(2);
    }

    @Override
    public int getItemCount() {
        return 5;
    }


   /* @Override
    public Filter getFilter() {
        if (mValueFilter == null) {
            mValueFilter = new ValueFilter();
        }
        return mValueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                List filterList = new ArrayList();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            mData = (List) results.values;
            notifyDataSetChanged();
        }

    }*/

}