package ua.com.zak.budgetswing.model.dao.factory;

import ua.com.zak.budgetswing.model.dao.AccountDao;

/**
 * @author zak <zak@swingpulse.com>
 */
public class SimpleDaoFactory implements DaoFactory {

    private final AccountDao mAccountDao;

    public SimpleDaoFactory() {
        mAccountDao = new SimpleAccountDaoImpl();
    }

    public AccountDao getAccountDao() {
        return mAccountDao;
    }
}
