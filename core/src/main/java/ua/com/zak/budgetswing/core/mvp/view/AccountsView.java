package ua.com.zak.budgetswing.core.mvp.view;

import java.util.List;

import ua.com.zak.budgetswing.core.domain.Account;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface AccountsView extends View {
    void displayAccounts(List<Account> accounts);
}
