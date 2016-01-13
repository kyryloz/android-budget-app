package ua.com.zak.budgetswing.model.dao.factory;

import ua.com.zak.budgetswing.model.dao.AccountDao;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface DaoFactory {
    AccountDao getAccountDao();
}
