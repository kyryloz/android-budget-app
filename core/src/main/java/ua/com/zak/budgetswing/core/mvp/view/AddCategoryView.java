package ua.com.zak.budgetswing.core.mvp.view;

import ua.com.zak.budgetswing.core.domain.Category;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface AddCategoryView extends View {
    void initEditMode(Category category);
}
