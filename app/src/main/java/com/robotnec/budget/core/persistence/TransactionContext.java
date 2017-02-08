package com.robotnec.budget.core.persistence;

/**
 * @author zak <zak@swingpulse.com>
 */
public interface TransactionContext {

    boolean doInTransaction(Transaction transaction);

    interface Transaction {
        boolean execute();
    }
}
