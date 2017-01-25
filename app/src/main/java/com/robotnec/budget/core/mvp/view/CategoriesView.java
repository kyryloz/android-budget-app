package com.robotnec.budget.core.mvp.view;

import java.util.List;

import com.robotnec.budget.core.domain.Category;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface CategoriesView extends View {
    void displayCategories(List<Category> categories);
}
