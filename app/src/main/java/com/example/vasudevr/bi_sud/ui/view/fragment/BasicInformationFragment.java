package com.example.vasudevr.bi_sud.ui.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vasudevr.bi_sud.R;
import com.example.vasudevr.bi_sud.ui.listener.IMain;
import com.example.vasudevr.bi_sud.ui.view.adapter.RecyclerAdapter;

/**
 * Created by vasudevr on 6/12/2017.
 */

public class BasicInformationFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_basic_information, container, false);
        return rootView;
    }
}
