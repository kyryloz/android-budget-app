package ua.com.zak.budgetswing.model.dao;

import java.util.List;

import ua.com.zak.budgetswing.model.domen.Account;
import ua.com.zak.budgetswing.model.domen.Transaction;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface AccountDao {
    List<Account> getAllAccounts();
    boolean addAccount(Account account);
    boolean removeAccount(long accountId);
    boolean makeTransaction(Transaction transaction);
}
