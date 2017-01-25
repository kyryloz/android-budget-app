package com.robotnec.budget.core.mvp.view;

import com.robotnec.budget.core.domain.Category;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface AddCategoryView extends View {
    void initEditMode(Category category);

    void closeView();
}
