package com.robotnec.budget.core.domain.operation;

import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.MoneyOperationDao;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Currency;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.service.CurrencyExchangeService;

import java.math.BigDecimal;

/**
 * @author zak <zak@swingpulse.com>
 */
public class OperationReceiverImpl implements OperationReceiver {
    private final MoneyOperationDao moneyOperationDao;
    private final AccountDao accountDao;
    private final CurrencyExchangeService exchangeService;


    public OperationReceiverImpl(MoneyOperationDao moneyOperationDao,
                                 AccountDao accountDao,
                                 CurrencyExchangeService exchangeService) {
        this.moneyOperationDao = moneyOperationDao;
        this.accountDao = accountDao;
        this.exchangeService = exchangeService;
    }

    @Override
    // TODO transaction
    public boolean receive(Expense expense) {
        MoneyAmount amount = expense.getAmount();
        Account account = expense.getAccount();
        MoneyAmount exchanged = exchangeService.exchange(amount, account.getAmount().getCurrency());
        account.setAmount(account.getAmount().substract(exchanged));
        accountDao.createOrUpdate(account);
        MoneyOperation entity = new MoneyOperation();
        entity.setCategory(expense.getCategory());
        entity.setDate(expense.getDate());
        entity.setAccount(expense.getAccount());
        entity.setAmount(expense.getAmount());
        return moneyOperationDao.createOrUpdate(entity);
    }

    @Override
    public boolean receive(Income income) {
        Account account = income.getAccount();
        MoneyAmount bonus = new MoneyAmount(new BigDecimal(1000), Currency.UAH);
        account.setAmount(account.getAmount().add(bonus));
        accountDao.createOrUpdate(account);
        MoneyOperation entity = new MoneyOperation();
        entity.setDate(income.getDate());
        entity.setAccount(income.getAccount());
        entity.setAmount(bonus);
        return moneyOperationDao.createOrUpdate(entity);
    }
}
