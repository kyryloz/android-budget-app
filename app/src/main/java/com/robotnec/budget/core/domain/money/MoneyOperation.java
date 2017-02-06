package com.robotnec.budget.core.domain.money;

import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.dao.MoneyOperationDao;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.service.CurrencyExchangeService;
import com.yahoo.squidb.data.TableModel;

import lombok.Data;

/**
 * @author zak <zak@swingpulse.com>
 */
@Data
public abstract class MoneyOperation {
    long id;
    MoneyAmount amount;
    long date;

    public abstract boolean execute(MoneyOperationDao moneyOperationDao,
                                    AccountDao accountDao,
                                    CurrencyExchangeService exchangeService);

    public abstract <T extends TableModel> T toRecord();
}
