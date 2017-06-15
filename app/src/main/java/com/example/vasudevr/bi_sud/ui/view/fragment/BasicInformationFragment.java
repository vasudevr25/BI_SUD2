package com.example.vasudevr.bi_sud.ui.view.fragment;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.telephony.PhoneNumberUtils;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vasudevr.bi_sud.R;
import com.example.vasudevr.bi_sud.network.model.BranchList;
import com.example.vasudevr.bi_sud.network.model.CityList;
import com.example.vasudevr.bi_sud.network.model.StateList;
import com.example.vasudevr.bi_sud.ui.helper.CityListHelper;
import com.example.vasudevr.bi_sud.ui.helper.StateListHelper;
import com.example.vasudevr.bi_sud.ui.listener.OnUpdateListListener;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by vasudevr on 6/12/2017.
 */

public class BasicInformationFragment extends Fragment implements View.OnClickListener
        , View.OnFocusChangeListener
        , TextView.OnEditorActionListener, AdapterView.OnItemSelectedListener {

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
    @InjectView(R.id.editEmail)
    EditText mEmailText;
    @InjectView(R.id.mailLayout)
    TextInputLayout mEmailTextLayout;
    @InjectView(R.id.editMobileNumber)
    EditText mEditNumber;
    @InjectView(R.id.mobileLayout)
    TextInputLayout mNumberLayout;
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
        StateListHelper helper = new StateListHelper(this);
        helper.callApi();
        setEditTextListener();
        return rootView;
    }

    private void setEditTextListener() {
        mEditDateText.setOnFocusChangeListener(this);
        mButtonNext.setOnClickListener(this);
        mEditDateText.setOnClickListener(this);
        mEmailText.setOnEditorActionListener(this);
        mEditNumber.setOnEditorActionListener(this);
        mSelectStateSpinner.setOnItemSelectedListener(this);
    }

    private void setStateListDetailsUI(ArrayList<StateList> stateList) {
        ArrayList<String> tempList = new ArrayList<>();
        for (int i = 0; i < stateList.size(); i++) {
            String listString = stateList.get(i).getValue().toString();
            tempList.add(listString);
        }
        String[] stringStateArray = tempList.toArray(new String[tempList.size()]);
        setSpinnerArray(stringStateArray, mSelectStateSpinner);
    }

    private void setCityListDetailsUI(ArrayList<CityList> cityList) {
        ArrayList<String> tempList = new ArrayList<>();
        for (int i = 0; i < cityList.size(); i++) {
            String listString = cityList.get(i).getCityName().toString();
            tempList.add(listString);
        }
        String[] stringStateArray = tempList.toArray(new String[tempList.size()]);
        setSpinnerArray(stringStateArray, mSelectStateSpinner);
    }

    private void setBranchListDetailsUI(ArrayList<BranchList> branchList) {
        ArrayList<String> tempList = new ArrayList<>();
        for (int i = 0; i < branchList.size(); i++) {
            String listString = branchList.get(i).getBranchName().toString();
            tempList.add(listString);
        }
        String[] stringStateArray = tempList.toArray(new String[tempList.size()]);
        //setSpinnerArray(stringStateArray, mSelectStateSpinner);
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidNumber(String number) {
        return !TextUtils.isEmpty(number) && Pattern.matches("^[7-9][0-9]{9}$", number);
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

    private void setSpinnerArray(String[] list, final MaterialBetterSpinner spinner) {

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

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {

            switch (v.getId()) {
                case R.id.editEmail:
                    String emailAddress = mEmailText.getText().toString().trim();

                    if (emailAddress.isEmpty() || !isValidEmail(emailAddress)) {
                        mEmailTextLayout.setError(getString(R.string.err_msg_email));
                        return false;
                    } else {
                        mEmailTextLayout.setErrorEnabled(false);
                    }
                    break;

                case R.id.editMobileNumber:
                    String mobileNumber = mEditNumber.getText().toString().trim();

                    if (mobileNumber.isEmpty() || !isValidNumber(mobileNumber)) {
                        mNumberLayout.setError(getString(R.string.err_msg_number));
                        return false;
                    } else {
                        mNumberLayout.setErrorEnabled(false);
                    }
                    break;
            }
        }
        return false;
    }

    public void setStateList(ArrayList<StateList> stateList) {
        setStateListDetailsUI(stateList);
    }

    public void setCityList(ArrayList<CityList> cityList) {
        setCityListDetailsUI(cityList);
    }

    public void setBranchList(ArrayList<BranchList> branchList) {
        setBranchListDetailsUI(branchList);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = mSelectStateSpinner.getText().toString();
        Log.d("BIF", "TEXT:::::" + text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Log.d("BIF", "TEXT:::::");
    }
}
