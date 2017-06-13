package com.example.vasudevr.bi_sud.ui.view.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vasudevr.bi_sud.R;
import com.example.vasudevr.bi_sud.ui.view.fragment.LandingFragment;

/**
 * Created by vasudevr on 6/9/2017.
 */

public class RecyclerVerticalViewAdapter extends RecyclerView.Adapter<RecyclerVerticalViewAdapter.MyViewHolder> implements Filterable {

    private Context mContext;
    private RecyclerHorizontalViewAdapter mRecyclerHorizontalViewAdapter;
    private LandingFragment mFragment;
    //ValueFilter mValueFilter;

    @Override
    public Filter getFilter() {
        return null;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView recyclerView;
        public TextView textView;


        public MyViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text_category);
            recyclerView = (RecyclerView) view.findViewById(R.id.horizontalScrollView);
        }
    }

    public RecyclerVerticalViewAdapter(Context context, LandingFragment fragment) {
        this.mContext = context;
        this.mFragment = fragment;
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
        holder.textView.setText(mContext.getString(R.string.text_category) + " " + categoryPosition);
        mRecyclerHorizontalViewAdapter  = new RecyclerHorizontalViewAdapter(mContext, mFragment);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerView.setLayoutManager(mLayoutManager);
        holder.recyclerView.setItemAnimator(new DefaultItemAnimator());
        holder.recyclerView.setAdapter(mRecyclerHorizontalViewAdapter);
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