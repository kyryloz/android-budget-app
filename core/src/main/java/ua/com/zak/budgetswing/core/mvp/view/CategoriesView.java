package ua.com.zak.budgetswing.core.mvp.view;

import java.util.List;

import ua.com.zak.budgetswing.core.domain.Category;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface CategoriesView extends View {
    void displayCategories(List<Category> categories);
}
