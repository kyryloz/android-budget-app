package ua.com.zak.budgetswing.core.dao.factory;

import java.util.ArrayList;
import java.util.List;

import ua.com.zak.budgetswing.core.dao.CategoryDao;
import ua.com.zak.budgetswing.core.domen.Category;

/**
 * @author zak <zak@swingpulse.com>
 */
class SimpleCategoryDao implements CategoryDao {

    private final List<Category> mCategories;

    public SimpleCategoryDao() {
        mCategories = new ArrayList<>();
        Category groceries = new Category();
        groceries.setId(1);
        groceries.setName("Groceries");
        Category transport = new Category();
        transport.setId(2);
        transport.setName("Transport");

        mCategories.add(groceries);
        mCategories.add(transport);
    }

    @Override
    public List<Category> getAllCategories() {
        return mCategories;
    }

    @Override
    public boolean addCategory(Category category) {
        return mCategories.add(category);
    }

    @Override
    public boolean removeCategory(long id) {
        return mCategories.remove(findById(id));
    }

    private Category findById(long id) {
        for (Category category : mCategories) {
            if (category.getId() == id) {
                return category;
            }
        }
        throw new IllegalArgumentException("No such category with id: " + id);
    }
}
