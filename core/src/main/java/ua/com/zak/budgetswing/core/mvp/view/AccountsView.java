package ua.com.zak.budgetswing.core.mvp.view;

import java.util.List;

import ua.com.zak.budgetswing.core.domen.Account;
import ua.com.zak.budgetswing.core.domen.Category;
import ua.com.zak.mvpcore.view.MvpView;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface AccountsView extends MvpView {
    void displayAccount(List<Account> accounts);

    void displayCategory(List<Category> categories);
}
