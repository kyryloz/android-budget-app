package ua.com.zak.budgetswing.core.mvp.view;

import java.util.Calendar;
import java.util.List;

import ua.com.zak.budgetswing.core.domain.Account;
import ua.com.zak.budgetswing.core.domain.Category;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface MakeTransactionView extends MvpView {
    void initDatePickerButton(String date);

    void initSubmitButtons();

    void showAccountsChooserDialog(List<Account> accounts);

    void showCategoryChooserDialog(List<Category> categories);

    void showChosenDate(String date);

    void showDateChooserDialog(Calendar lastDate);

    void displayAccount(Account account);

    void displayCategory(Category category);

    void finish();
}
