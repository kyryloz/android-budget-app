package ua.com.zak.budgetswing.core.dao;

import java.util.List;

import ua.com.zak.budgetswing.core.domain.Category;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface CategoryDao {
    List<Category> getAllCategories();
    boolean addCategory(Category category);
    boolean removeCategory(long id);
}
