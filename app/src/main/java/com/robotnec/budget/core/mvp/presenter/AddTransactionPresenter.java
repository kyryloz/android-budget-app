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
        getView().initDatePickerButton(format);

        List<Account> allAccounts = accountDao.getAll();
        targetAccount = allAccounts.get(0);
        getView().displayAccount(targetAccount);

        List<Category> allCategories = categoryDao.getAll();
        targetCategory = allCategories.get(0);
        getView().displayCategory(targetCategory);

        resultAmount = MoneyAmount.Companion.of(0, Currency.UAH);
        getView().displayInitialAmount(resultAmount);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public void changeAccount() {
        getView().showAccountsChooserDialog(accountDao.getAll());
    }

    public void changeCategory() {
        getView().showCategoryChooserDialog(categoryDao.getAll());
    }

    public void setChosenDate(Calendar calendar) {
        resultDate = calendar;
        getView().showChosenDate(dateFormat.format(calendar.getTime()));
    }

    public void changeDate() {
        getView().showDateChooserDialog(resultDate);
    }

    public void submit() {
        MoneyAmount moneyAmount = MoneyAmount.Companion.of(100, Currency.UAH);
        Expense expense = new Expense(
                moneyAmount,
                DateUtil.INSTANCE.fromSeconds(resultDate.getTimeInMillis() / 1000),
                targetAccount,
                targetCategory);
        moneyOperationBroker.execute(expense);
        getView().finish();
    }

    public void pickAccount(Account account) {
        targetAccount = account;
        getView().displayAccount(targetAccount);
    }

    public void pickCategory(Category category) {
        targetCategory = category;
        getView().displayCategory(category);
    }

    public void openCalculator() {
        getView().showCalculator(resultAmount);
    }
}
