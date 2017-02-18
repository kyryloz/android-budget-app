package com.robotnec.budget.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.robotnec.budget.R;
import com.robotnec.budget.core.domain.Currency;
import com.robotnec.budget.core.mvp.presenter.Presenter;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

/**
 * @author zak <zak@swingpulse.com>
 */
public class CalculatorFragment extends BasePresenterFragment {

//    @BindView(R.id.edit_amount)
//    EditText editAmount;
//
//    @BindView(R.id.spinner_currency)
//    Spinner spinnerAccountCurrency;

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected Presenter createPresenter() {
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                Currency.getAllCodes());
//        spinnerAccountCurrency.setAdapter(adapter);
    }
}
