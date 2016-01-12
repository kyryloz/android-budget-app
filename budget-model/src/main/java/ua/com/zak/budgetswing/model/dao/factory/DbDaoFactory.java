package ua.com.zak.budgetswing.model.dao.factory;

import ua.com.zak.budgetswing.model.dao.BudgetDao;

/**
 * @author zak <zak@swingpulse.com>
 */
public class DbDaoFactory implements DaoFactory {

    private final BudgetDao mBudgetDao;

    public DbDaoFactory() {
        mBudgetDao = new BudgetDaoImpl();
    }

    @Override
    public BudgetDao getBudgetDao() {
        return mBudgetDao;
    }
}
