package com.robotnec.budget.core.mvp.presenter;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.dao.TransactionDao;
import com.robotnec.budget.core.di.ApplicationComponent;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.domain.Transaction;
import com.robotnec.budget.core.mvp.view.AddTransactionView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddTransactionPresenter extends Presenter<AddTransactionView> {

    @Inject
    AccountDao mAccountDao;

    @Inject
    CategoryDao mCategoryDao;

    @Inject
    TransactionDao mTransactionDao;

    private Calendar mNowDate;

    private DateFormat mDateFormat;

    private Calendar mResultDate;
    private boolean mYesterdayChose;
    private Account mResultAccount;
    private Category mResultCategory;

    public AddTransactionPresenter(AddTransactionView view) {
        super(view);
        mNowDate = Calendar.getInstance();
        mResultDate = mNowDate;
        mYesterdayChose = false;
    }

    @Override
    public void onViewReady() {
        mDateFormat = DateFormat.getDateInstance();
        String format = mDateFormat.format(Calendar.getInstance().getTime());
        mView.initDatePickerButton(format);

        List<Account> allAccounts = mAccountDao.getAllAccounts();
        mResultAccount = allAccounts.get(0);
        mView.displayAccount(mResultAccount);

        List<Category> allCategories = mCategoryDao.getAllCategories();
        mResultCategory = allCategories.get(0);
        mView.displayCategory(mResultCategory);
    }

    @Override
    public void injectComponent(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public void setYesterdayChose(boolean chose) {
        mYesterdayChose = chose;
    }

    public void changeAccount() {
        mView.showAccountsChooserDialog(mAccountDao.getAllAccounts());
    }

    public void changeCategory() {
        mView.showCategoryChooserDialog(mCategoryDao.getAllCategories());
    }

    public void setChosenDate(Calendar calendar) {
        mResultDate = calendar;
        mView.showChosenDate(mDateFormat.format(calendar.getTime()));
    }

    public void changeDate() {
        mView.showDateChooserDialog(mResultDate);
    }

    public void submit(Long amount) {
        if (mYesterdayChose) {
            mNowDate.add(Calendar.DAY_OF_YEAR, -1);
            mResultDate = mNowDate;
        }

        Transaction transaction = new Transaction.Builder()
                .setAccount(mResultAccount)
                .setAmount(-amount)
                .setCurrency("UAH") // TODO hardcoded currency
                .setDate(mResultDate.getTimeInMillis())
                .setCategory(mResultCategory)
                .build();
        mTransactionDao.addTransaction(transaction);

        mView.finish();
    }

    public void pickAccount(Account account) {
        mResultAccount = account;
        mView.displayAccount(mResultAccount);
    }

    public void pickCategory(Category category) {
        mResultCategory = category;
        mView.displayCategory(category);
    }
}
