package com.robotnec.budget.core.domain.operation;

import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Currency;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.persistence.TransactionContext;
import com.robotnec.budget.core.persistence.dao.AccountDao;
import com.robotnec.budget.core.persistence.dao.TransactionDao;
import com.robotnec.budget.core.service.CurrencyExchangeService;

/**
 * @author zak <zak@swingpulse.com>
 */
public class OperationReceiverImpl implements OperationReceiver {

    private final TransactionDao transactionDao;
    private final AccountDao accountDao;
    private final CurrencyExchangeService exchangeService;
    private final TransactionContext transactionContext;

    public OperationReceiverImpl(TransactionDao transactionDao,
                                 AccountDao accountDao,
                                 CurrencyExchangeService exchangeService,
                                 TransactionContext transactionContext) {
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
        this.exchangeService = exchangeService;
        this.transactionContext = transactionContext;
    }

    @Override
    public boolean receive(Expense expense) {
        return transactionContext.doInTransaction(() -> {
            MoneyAmount amount = expense.getAmount();
            Account targetAccount = expense.getAccount();
            MoneyAmount exchanged = exchangeService.exchange(amount, targetAccount.getAmount().getCurrency());
            targetAccount.setAmount(targetAccount.getAmount().subtract(exchanged));
            boolean accountUpdated = accountDao.createOrUpdate(targetAccount);
            Transaction entity = new Transaction();
            entity.setCategory(expense.getCategory());
            entity.setDate(expense.getDate());
            entity.setAccount(expense.getAccount());
            entity.setAmount(expense.getAmount());
            boolean moneyOperationUpdated = transactionDao.createOrUpdate(entity);
            return accountUpdated && moneyOperationUpdated;
        });
    }

    @Override
    public boolean receive(Income income) {
        Account account = income.getAccount();
        MoneyAmount bonus = MoneyAmount.of(1000, Currency.UAH);
        account.setAmount(account.getAmount().add(bonus));
        accountDao.createOrUpdate(account);
        Transaction entity = new Transaction();
        entity.setDate(income.getDate());
        entity.setAccount(income.getAccount());
        entity.setAmount(bonus);
        return transactionDao.createOrUpdate(entity);
    }
}
