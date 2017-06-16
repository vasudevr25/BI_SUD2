package com.example.vasudevr.bi_sud.ui.view.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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
import com.example.vasudevr.bi_sud.ui.helper.BranchListHelper;
import com.example.vasudevr.bi_sud.ui.helper.CityListHelper;
import com.example.vasudevr.bi_sud.ui.helper.StateListHelper;
import com.example.vasudevr.bi_sud.ui.view.LandingActivity;
import com.example.vasudevr.bi_sud.utils.UiUtils;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by vasudevr on 6/12/2017.
 */

public class BasicInformationFragment extends Fragment implements View.OnClickListener
        , View.OnFocusChangeListener
        , TextView.OnEditorActionListener {

    @InjectView(R.id.selectState)
    MaterialBetterSpinner mSelectStateSpinner;
    @InjectView(R.id.selectCity)
    MaterialBetterSpinner mSelectCitySpinner;
    @InjectView(R.id.selectBranch)
    MaterialBetterSpinner mSelectBranchSpinner;
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

    private String mSelectedStateText = null;
    private String mSelectedCityText = null;
    private String mSelectedBranchText = null;
    ArrayList<BranchList> mBranchList;
    ArrayList<CityList> mCityList;
    ArrayList<StateList> mStateList;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_basic_information, container, false);
        ButterKnife.inject(this, rootView);
        setEditTextListener();
        StateListHelper helper = new StateListHelper(this, getActivity());
        helper.callApi();
        return rootView;
    }

    private void setEditTextListener() {
        mEditDateText.setOnFocusChangeListener(this);
        mButtonNext.setOnClickListener(this);
        mEditDateText.setOnClickListener(this);
        mEmailText.setOnEditorActionListener(this);
        mEditNumber.setOnEditorActionListener(this);
        mSelectStateSpinner.setOnClickListener(this);
        mSelectStateSpinner.setOnFocusChangeListener(this);
        mSelectCitySpinner.setOnClickListener(this);
        mSelectCitySpinner.setOnFocusChangeListener(this);
        mSelectBranchSpinner.setOnClickListener(this);
        mSelectBranchSpinner.setOnFocusChangeListener(this);

        String[] listSmoke = getResources().getStringArray(R.array.smoking_array);
        String[] listGender = getResources().getStringArray(R.array.gender_array);
        setBranchSpinnerArray(listSmoke, mSelectSmoking);
        setBranchSpinnerArray(listGender, mSelectGender);
    }

    private void setStateListDetailsUI(ArrayList<StateList> stateList) {
        ArrayList<String> tempList = new ArrayList<>();
        for (int i = 0; i < stateList.size(); i++) {
            String listString = stateList.get(i).getValue().toString();
            tempList.add(listString);
        }
        String[] stringStateArray = tempList.toArray(new String[tempList.size()]);
        setStateSpinnerArray(stringStateArray, stateList, mSelectStateSpinner);
    }

    private void setCityListDetailsUI(ArrayList<CityList> cityList) {
        ArrayList<String> tempList = new ArrayList<>();
        for (int i = 0; i < cityList.size(); i++) {
            String listString = cityList.get(i).getCityName().toString();
            tempList.add(listString);
        }
        String[] stringCityArray = tempList.toArray(new String[tempList.size()]);
        setCitySpinnerArray(stringCityArray, cityList, mSelectCitySpinner);
    }

    private void setBranchListDetailsUI(ArrayList<BranchList> branchList) {
        ArrayList<String> tempList = new ArrayList<>();
        for (int i = 0; i < branchList.size(); i++) {
            String listString = branchList.get(i).getBranchName().toString();
            tempList.add(listString);
        }
        String[] stringBranchArray = tempList.toArray(new String[tempList.size()]);
        setBranchSpinnerArray(stringBranchArray, mSelectBranchSpinner);
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

    private void setStateSpinnerArray(final String[] list, final ArrayList<StateList> stateList, final MaterialBetterSpinner spinner) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, list);
        spinner.setAdapter(adapter);
        spinner.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mSelectedStateText = spinner.getText().toString();
                Log.d("BIF", "StateSelectedText:::::" + mSelectedStateText);
                if (mSelectedCityText != null || mSelectedBranchText != null) {
                    mSelectCitySpinner.setText("");
                    mSelectBranchSpinner.setText("");
                }
                int id = 0;
                for (int i = 0; i < stateList.size(); i++) {
                    if (mSelectedStateText.equals(stateList.get(i).getValue().toString())) {
                        id = stateList.get(i).getKey();
                    }
                }
                CityListHelper helper = new CityListHelper(BasicInformationFragment.this, getActivity());
                if (id != 0) {
                    helper.callApi(id);
                }
            }
        });
    }

    private void setCitySpinnerArray(final String[] list, final ArrayList<CityList> cityList, final MaterialBetterSpinner spinner) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, list);
        spinner.setAdapter(adapter);
        spinner.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mSelectedCityText = spinner.getText().toString();
                if (mSelectedBranchText != null) {
                    mSelectBranchSpinner.setText("");
                }
                int id = 0;
                for (int i = 0; i < cityList.size(); i++) {
                    if (mSelectedCityText.equals(cityList.get(i).getCityName().toString())) {
                        id = cityList.get(i).getCityId();
                    }
                }
                BranchListHelper helper = new BranchListHelper(BasicInformationFragment.this, getActivity());
                if (id != 0) {
                    helper.callApi(id);
                }
            }
        });
    }

    private void setBranchSpinnerArray(final String[] list, final MaterialBetterSpinner spinner) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, list);
        spinner.setAdapter(adapter);
        spinner.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mSelectedBranchText = spinner.getText().toString();
            }
        });
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

            case R.id.selectState:
                if (UiUtils.checkInternetConnection(getActivity())) {
                    if (mStateList != null && !mStateList.isEmpty()) {
                        mSelectStateSpinner.setError(null);
                        setStateListDetailsUI(mStateList);
                    } else {
                        StateListHelper helper = new StateListHelper(this, getActivity());
                        helper.callApi();
                        /*ArrayAdapter<String> adapterState = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_dropdown_item_1line);
                        mSelectStateSpinner.setAdapter(adapterState);
                        mSelectCitySpinner.setAdapter(adapterState);
                        mSelectBranchSpinner.setAdapter(adapterState);
                        mSelectStateSpinner.setError("");*/
                    }
                } else {
                    UiUtils.showErrorAlert(getActivity().getString(R.string.error_dialog_header),
                            getActivity().getString(R.string.offline_mode_error), getActivity(), 0);
                }
                break;

            case R.id.selectCity:
                if (UiUtils.checkInternetConnection(getActivity())) {
                    if (mCityList != null && !mCityList.isEmpty()) {
                        mSelectCitySpinner.setError(null);
                        setCityListDetailsUI(mCityList);
                    } else {
                        ArrayAdapter<String> adapterCity = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_dropdown_item_1line);
                        mSelectCitySpinner.setAdapter(adapterCity);
                        mSelectBranchSpinner.setAdapter(adapterCity);
                        mSelectCitySpinner.setText("");
                        mSelectCitySpinner.setError(getString(R.string.err_msg_city));
                    }
                } else {
                    UiUtils.showErrorAlert(getActivity().getString(R.string.error_dialog_header),
                            getActivity().getString(R.string.offline_mode_error), getActivity(), 0);
                }
                break;

            case R.id.selectBranch:
                if (UiUtils.checkInternetConnection(getActivity())) {
                    if (mBranchList != null && !mBranchList.isEmpty()) {
                        mSelectBranchSpinner.setError(null);
                        setBranchListDetailsUI(mBranchList);
                    } else {
                        ArrayAdapter<String> adapterBranch = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_dropdown_item_1line);
                        mSelectBranchSpinner.setAdapter(adapterBranch);
                        mSelectBranchSpinner.setText("");
                        mSelectBranchSpinner.setError(getString(R.string.err_msg_branch));
                    }
                } else {
                    UiUtils.showErrorAlert(getActivity().getString(R.string.error_dialog_header),
                            getActivity().getString(R.string.offline_mode_error), getActivity(), 0);
                }
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

            case R.id.selectState:
                if (hasFocus) {
                    if (mStateList != null && !mStateList.isEmpty()) {
                        mSelectStateSpinner.setError(null);
                        setStateListDetailsUI(mStateList);
                    } else {
                        ArrayAdapter<String> adapterState = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_dropdown_item_1line);
                        mSelectStateSpinner.setAdapter(adapterState);
                        mSelectCitySpinner.setAdapter(adapterState);
                        mSelectBranchSpinner.setAdapter(adapterState);
                    }
                }
                break;

            case R.id.selectCity:
                if (hasFocus) {
                    if (mCityList != null && !mCityList.isEmpty()) {
                        mSelectCitySpinner.setError(null);
                        setCityListDetailsUI(mCityList);
                    } else {
                        ArrayAdapter<String> adapterCity = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_dropdown_item_1line);
                        mSelectCitySpinner.setAdapter(adapterCity);
                        mSelectBranchSpinner.setAdapter(adapterCity);
                        mSelectCitySpinner.setText("");
                        mSelectCitySpinner.setError(getString(R.string.err_msg_city));
                    }
                }
                break;

            case R.id.selectBranch:
                if (hasFocus) {
                    if (mBranchList != null && !mBranchList.isEmpty()) {
                        mSelectBranchSpinner.setError(null);
                        setBranchListDetailsUI(mBranchList);
                    } else {
                        ArrayAdapter<String> adapterBranch = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_dropdown_item_1line);
                        mSelectBranchSpinner.setAdapter(adapterBranch);
                        mSelectBranchSpinner.setText("");
                        mSelectBranchSpinner.setError(getString(R.string.err_msg_branch));
                    }
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
        mStateList = stateList;
        if (stateList != null && !stateList.isEmpty()) {
            setStateListDetailsUI(stateList);
        } else {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line);
            mSelectStateSpinner.setAdapter(adapter);
            mSelectCitySpinner.setAdapter(adapter);
            mSelectBranchSpinner.setAdapter(adapter);
        }

    }

    public void setCityList(ArrayList<CityList> cityList) {
        mCityList = cityList;
        if (cityList != null && !cityList.isEmpty()) {
            setCityListDetailsUI(cityList);
            mSelectBranchSpinner.setError(null);
        } else {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line);
            mSelectCitySpinner.setAdapter(adapter);
            mSelectBranchSpinner.setAdapter(adapter);

        }
    }

    public void setBranchList(ArrayList<BranchList> branchList) {
        mBranchList = branchList;
        if (branchList != null && !branchList.isEmpty()) {
            setBranchListDetailsUI(branchList);
            mSelectBranchSpinner.setError(null);
        } else {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line);
            mSelectBranchSpinner.setAdapter(adapter);
        }
    }
}
