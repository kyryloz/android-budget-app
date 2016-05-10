package ua.com.zak.budgetswing.core.mvp.view;

import ua.com.zak.budgetswing.core.domain.Account;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface AddAccountView extends View {
    void initEditMode(Account account);

    void closeView();
}
