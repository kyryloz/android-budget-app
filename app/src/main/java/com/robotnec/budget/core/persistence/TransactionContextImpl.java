package com.robotnec.budget.core.persistence;

import com.yahoo.squidb.data.SquidDatabase;

/**
 * @author zak <zak@swingpulse.com>
 */
public class TransactionContextImpl implements TransactionContext {

    private final SquidDatabase database;

    public TransactionContextImpl(SquidDatabase database) {
        this.database = database;
    }

    @Override
    public boolean doInTransaction(Transaction transaction) {
        boolean success = false;
        database.beginTransaction();
        try {
            success = transaction.execute();
            if (success) {
                database.setTransactionSuccessful();
            }
        } finally {
            database.endTransaction();
        }
        return success;
    }
}
