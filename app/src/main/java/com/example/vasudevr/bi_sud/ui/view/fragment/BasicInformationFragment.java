package com.example.vasudevr.bi_sud.ui.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.vasudevr.bi_sud.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

/**
 * Created by vasudevr on 6/12/2017.
 */

public class BasicInformationFragment extends Fragment {

    private MaterialBetterSpinner mSpinner;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_basic_information, container, false);
        mSpinner = (MaterialBetterSpinner) rootView.findViewById(R.id.selectState);
        String[] list = getResources().getStringArray(R.array.month);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, list);

        mSpinner.setAdapter(adapter);
        return rootView;
    }
}
