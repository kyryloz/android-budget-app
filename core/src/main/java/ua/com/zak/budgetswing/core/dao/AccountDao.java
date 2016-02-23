package ua.com.zak.budgetswing.core.dao;

import java.util.List;

import ua.com.zak.budgetswing.core.domain.Account;
import ua.com.zak.budgetswing.core.domain.Transaction;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface AccountDao {
    List<Account> getAllAccounts();
    boolean addAccount(Account account);
    boolean removeAccount(long accountId);
    boolean makeTransaction(Transaction transaction);
}
