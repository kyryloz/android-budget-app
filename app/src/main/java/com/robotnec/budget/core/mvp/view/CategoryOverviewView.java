package com.robotnec.budget.core.mvp.view;

import com.robotnec.budget.core.domain.money.MoneyOperation;

import java.util.List;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface CategoryOverviewView extends View {
    void displayCategoryTitle(String title);

    void displayCategoryTransactions(List<MoneyOperation> accounts);
}
