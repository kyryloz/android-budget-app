package ua.com.zak.budgetswing.core.mvp.view;

import java.util.List;

import ua.com.zak.budgetswing.core.domain.Account;
import ua.com.zak.budgetswing.core.domain.Category;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface AccountsView extends View {
    void displayAccount(List<Account> accounts);

    void displayCategory(List<Category> categories);
}
