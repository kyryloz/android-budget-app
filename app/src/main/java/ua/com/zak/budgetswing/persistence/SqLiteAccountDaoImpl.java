package ua.com.zak.budgetswing.persistence;

import java.util.List;

import ua.com.zak.budgetswing.model.dao.AccountDao;
import ua.com.zak.budgetswing.model.domen.Account;

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
    public boolean removeAccount(long accountId) {
        return false;
    }
}
