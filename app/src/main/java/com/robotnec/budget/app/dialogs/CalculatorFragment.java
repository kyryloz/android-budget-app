package com.robotnec.budget.app.dialogs;

import android.os.Bundle;

import com.robotnec.budget.R;
import com.robotnec.budget.app.fragments.BasePresenterFragment;
import com.robotnec.budget.app.util.Keys;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.mvp.presenter.CalculatorPresenter;
import com.robotnec.budget.core.mvp.view.CalculatorView;

public class CalculatorFragment extends BasePresenterFragment<CalculatorPresenter>
        implements CalculatorView {

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
}