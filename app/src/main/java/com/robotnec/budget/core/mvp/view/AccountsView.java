package com.robotnec.budget.core.mvp.view;

import java.util.List;

import com.robotnec.budget.core.domain.Account;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface AccountsView extends View {
    void displayAccounts(List<Account> accounts);
}
