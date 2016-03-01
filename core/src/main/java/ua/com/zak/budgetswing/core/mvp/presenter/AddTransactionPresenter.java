package ua.com.zak.budgetswing.core.mvp.presenter;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import ua.com.zak.budgetswing.core.dao.AccountDao;
import ua.com.zak.budgetswing.core.dao.CategoryDao;
import ua.com.zak.budgetswing.core.di.ApplicationComponent;
import ua.com.zak.budgetswing.core.domain.Account;
import ua.com.zak.budgetswing.core.domain.Category;
import ua.com.zak.budgetswing.core.domain.Transaction;
import ua.com.zak.budgetswing.core.mvp.view.AddTransactionView;

/**
 * @author zak <zak@swingpulse.com>
 */
public class AddTransactionPresenter extends Presenter<AddTransactionView> {

    @Inject
    AccountDao mAccountDao;

    @Inject
    CategoryDao mCategoryDao;

    private Calendar mNowDate;

    private java.text.DateFormat mDateFormat;

    private Calendar mResultDate;
    private boolean mYesterdayChose;
    private Account mResultAccount;
    private Category mResultCategory;

    public AddTransactionPresenter(AddTransactionView view) {
        super(view);
        mNowDate = Calendar.getInstance();
        mYesterdayChose = false;
    }

    @Override
    public void onViewReady() {
        mDateFormat = DateFormat.getDateInstance();
        String format = mDateFormat.format(Calendar.getInstance().getTime());
        mView.initDatePickerButton(format);
        mView.initSubmitButtons();

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
                .setAccountId(mResultAccount.getId())
                .setAmount(-amount)
                .setDate(new Date().getTime())
                .setCategoryId(mResultCategory.getId())
                .build();
        mAccountDao.makeTransaction(transaction);

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
