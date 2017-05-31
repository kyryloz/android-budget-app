package com.robotnec.budget.core.mvp.presenter;

import com.robotnec.budget.app.util.DateUtil;
import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.domain.Currency;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.domain.operation.Expense;
import com.robotnec.budget.core.mvp.view.AddTransactionView;
import com.robotnec.budget.core.persistence.dao.AccountDao;
import com.robotnec.budget.core.persistence.dao.CategoryDao;
import com.robotnec.budget.core.service.MoneyOperationBroker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddTransactionPresenter extends Presenter<AddTransactionView> {

    @Inject
    AccountDao accountDao;

    @Inject
    CategoryDao categoryDao;

    @Inject
    MoneyOperationBroker moneyOperationBroker;

    private DateFormat dateFormat;
    private Calendar resultDate;
    private Account targetAccount;
    private Category targetCategory;
    private MoneyAmount resultAmount;

    public AddTransactionPresenter(AddTransactionView view) {
        super(view);
        resultDate = Calendar.getInstance();
    }

    @Override
    public void onViewResume() {
        dateFormat = DateFormat.getDateInstance();
        String format = dateFormat.format(Calendar.getInstance().getTime());
        view.initDatePickerButton(format);

        List<Account> allAccounts = accountDao.getAll();
        targetAccount = allAccounts.get(0);
        view.displayAccount(targetAccount);

        List<Category> allCategories = categoryDao.getAll();
        targetCategory = allCategories.get(0);
        view.displayCategory(targetCategory);

        resultAmount = MoneyAmount.of(0, Currency.UAH);
        view.displayInitialAmount(resultAmount);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public void changeAccount() {
        view.showAccountsChooserDialog(accountDao.getAll());
    }

    public void changeCategory() {
        view.showCategoryChooserDialog(categoryDao.getAll());
    }

    public void setChosenDate(Calendar calendar) {
        resultDate = calendar;
        view.showChosenDate(dateFormat.format(calendar.getTime()));
    }

    public void changeDate() {
        view.showDateChooserDialog(resultDate);
    }

    public void submit() {
        MoneyAmount moneyAmount = MoneyAmount.of(100, Currency.UAH);
        Expense expense = new Expense(
                moneyAmount,
                DateUtil.fromSeconds(resultDate.getTimeInMillis() / 1000),
                targetAccount,
                targetCategory);
        moneyOperationBroker.execute(expense);
        view.finish();
    }

    public void pickAccount(Account account) {
        targetAccount = account;
        view.displayAccount(targetAccount);
    }

    public void pickCategory(Category category) {
        targetCategory = category;
        view.displayCategory(category);
    }

    public void openCalculator() {
        view.showCalculator(resultAmount);
    }
}
