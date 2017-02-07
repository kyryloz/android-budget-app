package com.robotnec.budget.core.domain.operation;

import com.robotnec.budget.BaseRobolectricTest;
import com.robotnec.budget.app.persistence.BudgetDatabase;
import com.robotnec.budget.app.persistence.MoneyOperationDaoImpl;
import com.robotnec.budget.app.persistence.SquidbAccountDao;
import com.robotnec.budget.app.persistence.SquidbCategoryDao;
import com.robotnec.budget.app.service.SimpleCurrencyExchangeService;
import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.dao.MoneyOperationDao;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.domain.Currency;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.service.CurrencyExchangeService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;

import java.math.BigDecimal;

/**
 * @author zak <zak@swingpulse.com>
 */
public class OperationReceiverImplTest extends BaseRobolectricTest {

    private OperationReceiver operationReceiver;
    private MoneyOperationDao moneyOperationDao;
    private Account testAccount;
    private Category testCategory;

    @Before
    public void before() {
        BudgetDatabase database = new BudgetDatabase(RuntimeEnvironment.application);
        AccountDao accountDao = new SquidbAccountDao(database);
        CategoryDao categoryDao = new SquidbCategoryDao(database);
        CurrencyExchangeService exchangeService = new SimpleCurrencyExchangeService();
        moneyOperationDao = new MoneyOperationDaoImpl(database, accountDao, categoryDao);
        operationReceiver = new OperationReceiverImpl(moneyOperationDao, accountDao, exchangeService);

        testAccount = new Account();
        testAccount.setAmount(new MoneyAmount(new BigDecimal(0), Currency.UAH));
        testAccount.setName("account");
        accountDao.createOrUpdate(testAccount);

        testCategory = new Category();
        testCategory.setName("category");
        categoryDao.createOrUpdate(testCategory);
    }

    @Test
    public void textTest() {
        Expense expense = new Expense();
        expense.setAccount(testAccount);
        expense.setCategory(testCategory);
        expense.setAmount(new MoneyAmount(new BigDecimal(100), Currency.UAH));
        boolean success = operationReceiver.receive(expense);
        Assert.assertTrue(success);
    }
}