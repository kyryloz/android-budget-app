package ua.com.zak.budgetswing.model.dao.factory;

import ua.com.zak.budgetswing.model.dao.BudgetDao;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface DaoFactory {
    BudgetDao getBudgetDao();
}
