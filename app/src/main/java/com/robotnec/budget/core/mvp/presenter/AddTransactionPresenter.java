package com.robotnec.budget.core.mvp.presenter;

import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.domain.Currency;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.domain.money.Expense;
import com.robotnec.budget.core.mvp.view.AddTransactionView;
import com.robotnec.budget.core.service.MoneyOperationService;

import java.math.BigDecimal;
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
    MoneyOperationService moneyOperationService;

    private DateFormat dateFormat;
    private Calendar nowDate;
    private Calendar resultDate;
    private boolean yesterdayChose;
    private Account targetAccount;
    private Category targetCategory;

    public AddTransactionPresenter(AddTransactionView view) {
        super(view);
        nowDate = Calendar.getInstance();
        resultDate = nowDate;
        yesterdayChose = false;
    }

    @Override
    public void onViewReady() {
        dateFormat = DateFormat.getDateInstance();
        String format = dateFormat.format(Calendar.getInstance().getTime());
        view.initDatePickerButton(format);

        List<Account> allAccounts = accountDao.getAll();
        targetAccount = allAccounts.get(0);
        view.displayAccount(targetAccount);

        List<Category> allCategories = categoryDao.getAll();
        targetCategory = allCategories.get(0);
        view.displayCategory(targetCategory);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public void setYesterdayChose(boolean chose) {
        yesterdayChose = chose;
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

    public void submit(Long amount, String currencyCode) {
        if (yesterdayChose) {
            nowDate.add(Calendar.DAY_OF_YEAR, -1);
            resultDate = nowDate;
        }
        MoneyAmount moneyAmount = new MoneyAmount(new BigDecimal(-amount), Currency.fromCode(currencyCode));
        Expense expense = new Expense();
        expense.setAccount(targetAccount);
        expense.setAmount(moneyAmount);
        expense.setDate(resultDate.getTimeInMillis());
        expense.setCategory(targetCategory);
        moneyOperationService.execute(expense);

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
}
