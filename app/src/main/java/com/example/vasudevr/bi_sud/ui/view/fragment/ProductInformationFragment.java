package com.example.vasudevr.bi_sud.ui.view.fragment;

import android.icu.text.Normalizer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vasudevr.bi_sud.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

/**
 * Created by vasudevr on 6/14/2017.
 */

public class ProductInformationFragment extends Fragment {

    private LinearLayout mProductInformationLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_information, container, false);
        mProductInformationLayout = (LinearLayout) rootView.findViewById(R.id.product_information_layout);
        createProductInformationUI();
        return rootView;
    }

    private void createProductInformationUI() {
        LinearLayout.LayoutParams editTextLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        EditText editText = new EditText(getActivity());
        editText.setHint("Product");
        editText.setTextSize(16);
        editText.setLayoutParams(editTextLayoutParams);
        TextInputLayout textInputLayout = new TextInputLayout(getActivity());
        LinearLayout.LayoutParams textInputLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textInputLayout.setLayoutParams(textInputLayoutParams);

        // Then you add editText into a textInputLayout
        textInputLayout.addView(editText, editTextLayoutParams);
        mProductInformationLayout.addView(textInputLayout, textInputLayoutParams);
        LinearLayout.LayoutParams spinnerLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        MaterialBetterSpinner spinner = new MaterialBetterSpinner(getActivity());
        spinner.setHint("HELLO");
        spinner.setLayoutParams(spinnerLayoutParams);
        mProductInformationLayout.addView(spinner, spinnerLayoutParams);
        /*Button btn = new Button(getActivity());
        btn.setText("Button");
        mProductInformationLayout.addView(btn, lpView);*/
    }
}
