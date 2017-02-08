package com.robotnec.budget.core.persistence.dao;

import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */

public interface BaseDao<T> {

    T findById(long id);

    List<T> getAll();

    boolean remove(long id);

    boolean createOrUpdate(T entity);
}
