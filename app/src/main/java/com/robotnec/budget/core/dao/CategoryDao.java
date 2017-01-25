package com.robotnec.budget.core.dao;

import java.util.List;

import com.robotnec.budget.core.domain.Category;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface CategoryDao {
    List<Category> getAllCategories();

    boolean addCategory(Category category);

    void updateCategory(Category category);

    boolean removeCategory(long id);
}
