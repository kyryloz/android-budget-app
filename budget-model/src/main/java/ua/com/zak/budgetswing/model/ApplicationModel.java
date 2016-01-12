package ua.com.zak.budgetswing.model;

import ua.com.zak.budgetswing.model.dao.factory.DaoFactory;

/**
 * @author zak <zak@swingpulse.com>
 */
public class ApplicationModel {

    private final DaoFactory mDaoFactory;

    public ApplicationModel(DaoFactory daoFactory) {
        mDaoFactory = daoFactory;
    }

    public DaoFactory getDaoFactory() {
        return mDaoFactory;
    }
}
