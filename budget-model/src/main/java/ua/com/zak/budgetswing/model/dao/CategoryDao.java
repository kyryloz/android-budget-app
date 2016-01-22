package ua.com.zak.budgetswing.model.dao;

import java.util.List;

import ua.com.zak.budgetswing.model.domen.Category;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface CategoryDao {
    List<Category> getAllCategories();
    boolean addCategory(Category category);
    boolean removeCategory(long id);
}
