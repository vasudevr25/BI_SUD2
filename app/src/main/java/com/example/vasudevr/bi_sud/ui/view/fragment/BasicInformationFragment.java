package com.example.vasudevr.bi_sud.ui.view.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.vasudevr.bi_sud.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by vasudevr on 6/12/2017.
 */

public class BasicInformationFragment extends Fragment implements View.OnClickListener, View.OnFocusChangeListener {

    @InjectView(R.id.selectState)
    MaterialBetterSpinner mSelectStateSpinner;
    @InjectView(R.id.button_next)
    Button mButtonNext;
    @InjectView(R.id.editDatePicker)
    EditText mEditDateText;
    @InjectView(R.id.selectSmoking)
    MaterialBetterSpinner mSelectSmoking;
    @InjectView(R.id.selectGender)
    MaterialBetterSpinner mSelectGender;
    private int mYear, mMonth, mDay;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_basic_information, container, false);
        ButterKnife.inject(this, rootView);
        mEditDateText.setOnFocusChangeListener(this);
        mButtonNext.setOnClickListener(this);
        mEditDateText.setOnClickListener(this);

        String[] listState = getResources().getStringArray(R.array.month);
        String[] listSmoke = getResources().getStringArray(R.array.smoking_array);
        String[] listGender = getResources().getStringArray(R.array.gender_array);
        setSpinnerArray(listState, mSelectStateSpinner);
        setSpinnerArray(listSmoke, mSelectSmoking);
        setSpinnerArray(listGender, mSelectGender);

        return rootView;
    }

    private void setDateField() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        mEditDateText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void setSpinnerArray(String[] list, MaterialBetterSpinner spinner) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, list);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_next:
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new ProductInformationFragment())
                        .commitNow();
                break;
            case R.id.editDatePicker:
                setDateField();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.editDatePicker:
                if (hasFocus) {
                    setDateField();
                }
                break;
        }
    }
}
