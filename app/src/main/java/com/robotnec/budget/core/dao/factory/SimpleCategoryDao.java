package com.robotnec.budget.core.dao.factory;

import java.util.ArrayList;
import java.util.List;

import com.robotnec.budget.core.dao.CategoryDao;
import com.robotnec.budget.core.domain.Category;

/**
 * @author zak <zak@swingpulse.com>
 */
public class SimpleCategoryDao implements CategoryDao {

    private final List<Category> categories;

    public SimpleCategoryDao() {
        categories = new ArrayList<>();
        Category groceries = new Category();
        groceries.setId(1);
        groceries.setName("Groceries");
        Category transport = new Category();
        transport.setId(2);
        transport.setName("Transport");

        categories.add(groceries);
        categories.add(transport);
    }

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public boolean addCategory(Category category) {
        return categories.add(category);
    }

    @Override
    public void updateCategory(Category category) {
        Category edited = findById(category.getId());
        edited.setName(category.getName());
    }

    @Override
    public boolean removeCategory(long id) {
        return categories.remove(findById(id));
    }

    private Category findById(long id) {
        for (Category category : categories) {
            if (category.getId() == id) {
                return category;
            }
        }
        throw new IllegalArgumentException("No such category with id: " + id);
    }
}
