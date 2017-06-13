package com.example.vasudevr.bi_sud.ui.view.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vasudevr.bi_sud.R;
import com.example.vasudevr.bi_sud.ui.view.adapter.RecyclerVerticalViewAdapter;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by vasudevr on 6/12/2017.
 */

public class LandingFragment extends Fragment implements TextView.OnEditorActionListener, TextWatcher {

    private RecyclerView mRecyclerView;
    private RecyclerVerticalViewAdapter mRecyclerAdapter;
    private EditText mEditSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_landing, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mEditSearch = (EditText) rootView.findViewById(R.id.editSearch);
        mEditSearch.addTextChangedListener(this);
        mEditSearch.setOnEditorActionListener(this);

        mRecyclerAdapter = new RecyclerVerticalViewAdapter(getActivity(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mRecyclerAdapter);
        return rootView;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (actionId == EditorInfo.IME_ACTION_SEARCH) {

            switch (v.getId()) {
                case R.id.editSearch:
                    doSearch();
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    //mEditSearch.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_search_black_18dp, 0);
                    break;
            }
        }
        return false;
    }

    public void doSearch() {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(mEditSearch.isFocusable() && mEditSearch.length() != 0) {
            mEditSearch.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_clear_black_18dp, 0);
        } else {
            mEditSearch.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_search_black_18dp, 0);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
