package com.robotnec.budget.app.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.robotnec.budget.R;
import com.robotnec.budget.app.util.Keys;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.mvp.presenter.CalculatorPresenter;
import com.robotnec.budget.core.mvp.view.CalculatorView;

import butterknife.BindView;
import butterknife.OnClick;

public class CalculatorFragment extends BasePresenterFragment<CalculatorPresenter>
        implements CalculatorView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.text_input)
    TextView textInput;

    private int originalTextColor;

    public static CalculatorFragment newInstance(MoneyAmount initialAmount) {
        Bundle args = new Bundle();
        args.putSerializable(Keys.AMOUNT, initialAmount);
        CalculatorFragment fragment = new CalculatorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_calculator;
    }

    @Override
    protected CalculatorPresenter createPresenter() {
        return new CalculatorPresenter(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbarBack(toolbar);
        toolbar.setTitle(null);
        toolbar.setNavigationOnClickListener(v -> close());
        originalTextColor = textInput.getCurrentTextColor();
    }

    @OnClick({R.id.digit_0, R.id.digit_1, R.id.digit_2, R.id.digit_3, R.id.digit_4,
            R.id.digit_5, R.id.digit_6, R.id.digit_7, R.id.digit_8, R.id.digit_9})
    void onDigitClick(View view) {
        switch (view.getId()) {
            case R.id.digit_0:
                presenter.digit(0);
                break;
            case R.id.digit_1:
                presenter.digit(1);
                break;
            case R.id.digit_2:
                presenter.digit(2);
                break;
            case R.id.digit_3:
                presenter.digit(3);
                break;
            case R.id.digit_4:
                presenter.digit(4);
                break;
            case R.id.digit_5:
                presenter.digit(5);
                break;
            case R.id.digit_6:
                presenter.digit(6);
                break;
            case R.id.digit_7:
                presenter.digit(7);
                break;
            case R.id.digit_8:
                presenter.digit(8);
                break;
            case R.id.digit_9:
                presenter.digit(9);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @OnClick({R.id.operation_divide, R.id.operation_multiply,
            R.id.operation_minus, R.id.operation_plus})
    void onOperationClick(View view) {
        switch (view.getId()) {
            case R.id.operation_divide:
                presenter.divide();
                break;
            case R.id.operation_multiply:
                presenter.multiply();
                break;
            case R.id.operation_minus:
                presenter.minus();
                break;
            case R.id.operation_plus:
                presenter.plus();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @OnClick(R.id.dot)
    void onDotClick() {
        presenter.dot();
    }

    @OnClick(R.id.calculate)
    void onCalculateClick() {
        presenter.calculate();
    }

    @OnClick(R.id.back)
    void onBackClick() {
        presenter.back();
    }

    @OnClick(R.id.clear)
    void onClearClick() {
        presenter.clear();
    }

    @Override
    public void display(String value) {
        textInput.setTextColor(originalTextColor);
        textInput.setText(value);
    }

    @Override
    public void displayError() {
        textInput.setTextColor(Color.RED);
    }

    @Override
    public void close() {
        getFragmentManager().beginTransaction()
                .remove(this)
                .commit();
    }
}