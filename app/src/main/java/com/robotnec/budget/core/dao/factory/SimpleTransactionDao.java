package com.robotnec.budget.core.dao.factory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.dao.TransactionDao;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Transaction;

/**
 * @author zak <zak@swingpulse.com>
 */
public class SimpleTransactionDao implements TransactionDao {

    private final AccountDao accountDao;
    private final CategoryDao categoryDao;

    private final List<Transaction> transactions;

    public SimpleTransactionDao(AccountDao accountDao, CategoryDao categoryDao) {
        this.accountDao = accountDao;
        this.categoryDao = categoryDao;
        transactions = new ArrayList<>();
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    @Override
    public boolean addTransaction(Transaction transaction) {
        Account account = transaction.getAccount();
        account.setAmount(account.getAmount().add(new BigDecimal(transaction.getAmount())));
        accountDao.updateAccount(account);

        transactions.add(transaction);

        return true;
    }

    @Override
    public List<Transaction> getTransactionsForCategory(long categoryId) {
        return Stream.of(transactions)
                .filter(t -> t.getCategory().getId() == categoryId)
                .collect(Collectors.toList());
    }
}
