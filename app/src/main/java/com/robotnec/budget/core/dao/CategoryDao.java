package com.robotnec.budget.core.dao;

import com.robotnec.budget.core.domain.Category;

import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface CategoryDao {
    Category findById(long id);

    List<Category> getAllCategories();

    boolean addCategory(Category category);

    void updateCategory(Category category);

    boolean removeCategory(long id);
}
