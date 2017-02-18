package com.robotnec.budget.core.mvp.view;

import java.util.Calendar;
import java.util.List;

import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.domain.MoneyAmount;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface AddTransactionView extends View {
    void initDatePickerButton(String date);

    void showAccountsChooserDialog(List<Account> accounts);

    void showCategoryChooserDialog(List<Category> categories);

    void showChosenDate(String date);

    void showDateChooserDialog(Calendar lastDate);

    void showCalculator(MoneyAmount initialAmount);

    void displayAccount(Account account);

    void displayCategory(Category category);

    void displayInitialAmount(MoneyAmount initialAmount);

    void finish();
}
