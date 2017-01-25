package com.robotnec.budget.persistence;

import java.util.List;

import com.robotnec.budget.core.dao.AccountDao;
import com.robotnec.budget.core.domain.Account;

/**
 * @author zak <zak@swingpulse.com>
 */
public class SqLiteAccountDaoImpl implements AccountDao {

    @Override
    public List<Account> getAllAccounts() {
        return null;
    }

    @Override
    public boolean addAccount(Account account) {
        return false;
    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public boolean removeAccount(long accountId) {
        return false;
    }

    @Override
    public Account findById(long accountId) {
        return null;
    }
}
