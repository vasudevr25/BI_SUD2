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
import android.widget.TextView;

import com.example.vasudevr.bi_sud.R;
import com.example.vasudevr.bi_sud.network.model.ProductList;
import com.example.vasudevr.bi_sud.ui.view.fragment.LandingFragment;

import java.util.ArrayList;

/**
 * Created by vasudevr on 6/9/2017.
 */

public class RecyclerVerticalViewAdapter extends RecyclerView.Adapter<RecyclerVerticalViewAdapter.MyViewHolder> implements Filterable {

    private Context mContext;
    private RecyclerHorizontalViewAdapter mRecyclerHorizontalViewAdapter;
    private LandingFragment mFragment;
    private ArrayList<ProductList> productArrayList;
    private ArrayList<ProductList> filteredList;
    ValueFilter mValueFilter;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView recyclerView;
        public TextView textView;


        public MyViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text_category);
            recyclerView = (RecyclerView) view.findViewById(R.id.horizontalScrollView);
        }
    }

    public RecyclerVerticalViewAdapter(Context context, LandingFragment fragment, ArrayList<ProductList> arrayList) {
        this.mContext = context;
        this.mFragment = fragment;
        this.productArrayList = arrayList;
        this.filteredList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_vertical_list_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int categoryPosition = position + 1;
        if(productArrayList != null && productArrayList.get(position).getCategoryId().equals(categoryPosition)) {
            holder.textView.setText(mContext.getString(R.string.text_category) + " " + categoryPosition);
            mRecyclerHorizontalViewAdapter = new RecyclerHorizontalViewAdapter(mContext, mFragment, filteredList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            holder.recyclerView.setLayoutManager(mLayoutManager);
            holder.recyclerView.setItemAnimator(new DefaultItemAnimator());
            holder.recyclerView.setAdapter(mRecyclerHorizontalViewAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }


    @Override
    public Filter getFilter() {
        if (mValueFilter == null) {
            mValueFilter = new ValueFilter();
        }
        return mValueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint!=null && constraint.length()>0) {
                ArrayList<ProductList> tempList = new ArrayList();

                // search content in friend list
                for (ProductList productData : productArrayList) {
                    if (productData.getProductName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(productData);
                    }
                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = productArrayList.size();
                filterResults.values = productArrayList;
            }

            return filterResults;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            filteredList = (ArrayList<ProductList>) results.values;
            notifyDataSetChanged();
        }

    }

}