package com.robotnec.budget.core.domain.operation;

import com.robotnec.budget.BaseRobolectricTest;
import com.robotnec.budget.core.persistence.BudgetDatabase;
import com.robotnec.budget.core.persistence.TransactionContext;
import com.robotnec.budget.core.persistence.TransactionContextImpl;
import com.robotnec.budget.core.persistence.dao.TransactionDao;
import com.robotnec.budget.core.persistence.dao.impl.TransactionDaoImpl;
import com.robotnec.budget.core.persistence.dao.impl.AccountDaoImpl;
import com.robotnec.budget.core.persistence.dao.impl.CategoryDaoImpl;
import com.robotnec.budget.core.service.impl.SimpleCurrencyExchangeService;
import com.robotnec.budget.core.persistence.dao.AccountDao;
import com.robotnec.budget.core.persistence.dao.CategoryDao;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.domain.Currency;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.service.CurrencyExchangeService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.robolectric.RuntimeEnvironment;
import org.threeten.bp.LocalDateTime;

import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public class OperationReceiverImplTest extends BaseRobolectricTest {

    private TransactionDao transactionDao;
    private CurrencyExchangeService exchangeService;
    private TransactionContext transactionContext;
    private Account testAccount;
    private Category testCategory;
    private AccountDao accountDao;

    @Before
    public void before() throws Exception {
        BudgetDatabase database = new BudgetDatabase(RuntimeEnvironment.application);
        accountDao = new AccountDaoImpl(database);
        CategoryDao categoryDao = new CategoryDaoImpl(database);
        exchangeService = new SimpleCurrencyExchangeService();
        transactionContext = new TransactionContextImpl(database);
        transactionDao = new TransactionDaoImpl(database, accountDao, categoryDao);

        testAccount = new Account();
        testAccount.setAmount(MoneyAmount.of(100, Currency.UAH));
        testAccount.setName("account");
        accountDao.createOrUpdate(testAccount);

        testCategory = new Category();
        testCategory.setName("category");
        categoryDao.createOrUpdate(testCategory);
    }

    @Test
    public void shouldPerformExpenseOperation() throws Exception {
        OperationReceiver operationReceiver = new OperationReceiverImpl(transactionDao,
                accountDao,
                exchangeService, transactionContext);
        Expense expense = new Expense();
        expense.setAccount(testAccount);
        expense.setCategory(testCategory);
        expense.setDate(LocalDateTime.now());
        expense.setAmount(MoneyAmount.of(10, Currency.UAH));

        boolean success = operationReceiver.receive(expense);
        Assert.assertTrue(success);

        MoneyAmount actual = accountDao.findById(testAccount.getId()).getAmount();
        MoneyAmount ninety = MoneyAmount.of(90, Currency.UAH);
        Assert.assertEquals(ninety, actual);

        List<Transaction> actualOperations = transactionDao.getAll();
        Assert.assertEquals(1, actualOperations.size());

        Transaction actualOperation = actualOperations.get(0);

        MoneyAmount actualOperationAmount = actualOperation.getAmount();
        MoneyAmount ten = MoneyAmount.of(10, Currency.UAH);
        Assert.assertEquals(ten, actualOperationAmount);
    }

    @Test
    public void shouldRollbackAccountChangeIfFail() throws Exception {
        TransactionDao mockTransactionDao = Mockito.mock(TransactionDao.class);
        Mockito.when(mockTransactionDao.createOrUpdate(Mockito.any())).thenReturn(false);
        OperationReceiver operationReceiver = new OperationReceiverImpl(mockTransactionDao,
                accountDao,
                exchangeService, transactionContext);

        Expense expense = new Expense();
        expense.setAccount(testAccount);
        expense.setCategory(testCategory);
        expense.setAmount(MoneyAmount.of(10, Currency.UAH));

        boolean success = operationReceiver.receive(expense);
        Assert.assertFalse(success);

        MoneyAmount actual = accountDao.findById(testAccount.getId()).getAmount();
        MoneyAmount ninety = MoneyAmount.of(90, Currency.UAH);
        Assert.assertNotEquals(ninety, actual);

        List<Transaction> actualOperations = transactionDao.getAll();
        Assert.assertEquals(0, actualOperations.size());
    }
}