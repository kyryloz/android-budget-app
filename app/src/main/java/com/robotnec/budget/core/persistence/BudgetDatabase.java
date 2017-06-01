package com.robotnec.budget.core.persistence;

import android.content.Context;

import com.robotnec.budget.core.domain.Currency;
import com.robotnec.budget.core.domain.MoneyAmount;
import com.robotnec.budget.core.persistence.schema.AccountRecord;
import com.robotnec.budget.core.persistence.schema.CategoryRecord;
import com.robotnec.budget.core.persistence.schema.TransactionRecord;
import com.yahoo.squidb.android.AndroidOpenHelper;
import com.yahoo.squidb.data.ISQLiteDatabase;
import com.yahoo.squidb.data.ISQLiteOpenHelper;
import com.yahoo.squidb.data.SquidDatabase;
import com.yahoo.squidb.sql.Table;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BudgetDatabase extends SquidDatabase {

    private static final int VERSION = 1;

    private final Context context;

    @Inject
    public BudgetDatabase(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected ISQLiteOpenHelper createOpenHelper(String databaseName, OpenHelperDelegate delegate, int version) {
        return new AndroidOpenHelper(context, databaseName, delegate, version);
    }

    @Override
    public String getName() {
        return "budget.db";
    }

    @Override
    protected Table[] getTables() {
        return new Table[]{
                AccountRecord.TABLE,
                CategoryRecord.TABLE,
                TransactionRecord.TABLE
        };
    }

    @Override
    protected boolean onUpgrade(ISQLiteDatabase db, int oldVersion, int newVersion) {
        return false;
    }

    @Override
    protected void onTablesCreated(ISQLiteDatabase db) {
        super.onTablesCreated(db);
        AccountRecord wallet = new AccountRecord()
                .setName("Wallet")
                .setAmount(MoneyAmount.Companion.of(10000, Currency.UAH).toDbString());
        AccountRecord creditCard = new AccountRecord()
                .setName("Credit Card")
                .setAmount(MoneyAmount.Companion.of(10000, Currency.UAH).toDbString());
        CategoryRecord food = new CategoryRecord()
                .setName("Food");
        CategoryRecord taxi = new CategoryRecord()
                .setName("Taxi");
        persist(wallet);
        persist(creditCard);
        persist(food);
        persist(taxi);
    }

    @Override
    protected void onConfigure(ISQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    protected int getVersion() {
        return VERSION;
    }
}
