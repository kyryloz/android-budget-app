package com.robotnec.budget.core.dao;

import java.util.List;

import com.robotnec.budget.core.domain.Account;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface AccountDao {
    List<Account> getAllAccounts();

    boolean addAccount(Account account);

    void updateAccount(Account account);

    boolean removeAccount(long accountId);

    Account findById(long accountId);
}
