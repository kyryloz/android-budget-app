package com.robotnec.budget.core.mvp.view;

import com.robotnec.budget.core.domain.Account;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface AddAccountView extends View {
    void initEditMode(Account account);

    void closeView();
}
