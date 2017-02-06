package com.robotnec.budget.core.domain.money;

import com.robotnec.budget.app.persistence.schema.MoneyOperationRecord;
import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.MoneyOperationDao;
import com.robotnec.budget.core.domain.Account;
import com.robotnec.budget.core.domain.Category;
import com.robotnec.budget.core.service.CurrencyExchangeService;
import com.yahoo.squidb.data.TableModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zak <zak@swingpulse.com>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Expense extends MoneyOperation {
    private Account account;
    private Category category;

    @Override
    public boolean execute(MoneyOperationDao moneyOperationDao,
                           AccountDao accountDao,
                           CurrencyExchangeService exchangeService) {
        account.setAmount(account.getAmount().add(exchangeService, amount));
        accountDao.createOrUpdate(account);
        return moneyOperationDao.createOrUpdate(this);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends TableModel> T toRecord() {
        return (T) new MoneyOperationRecord()
                .setId(id)
                .setAccountId(account.getId())
                .setCategoryId(category.getId())
                .setAmount(amount.toDbString())
                .setDate(date);
    }
}
