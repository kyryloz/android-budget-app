package com.robotnec.budget.core.persistence

import android.content.Context

import com.robotnec.budget.core.domain.Currency
import com.robotnec.budget.core.domain.MoneyAmount
import com.robotnec.budget.core.persistence.schema.AccountRecord
import com.robotnec.budget.core.persistence.schema.CategoryRecord
import com.robotnec.budget.core.persistence.schema.TransactionRecord
import com.yahoo.squidb.android.AndroidOpenHelper
import com.yahoo.squidb.data.ISQLiteDatabase
import com.yahoo.squidb.data.ISQLiteOpenHelper
import com.yahoo.squidb.data.SquidDatabase
import com.yahoo.squidb.sql.Table

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BudgetDatabase @Inject constructor(private val context: Context) : SquidDatabase() {

    override fun createOpenHelper(databaseName: String, delegate: SquidDatabase.OpenHelperDelegate, version: Int): ISQLiteOpenHelper {
        return AndroidOpenHelper(context, databaseName, delegate, version)
    }

    override fun getName(): String {
        return "budget.db"
    }

    override fun getTables(): Array<Table> {
        return arrayOf(AccountRecord.TABLE, CategoryRecord.TABLE, TransactionRecord.TABLE)
    }

    override fun onUpgrade(db: ISQLiteDatabase, oldVersion: Int, newVersion: Int): Boolean {
        return false
    }

    override fun onTablesCreated(db: ISQLiteDatabase?) {
        super.onTablesCreated(db)
        val wallet = AccountRecord()
                .setName("Wallet")
                .setAmount(MoneyAmount.of(10000.0, Currency.UAH).toDbString())
        val creditCard = AccountRecord()
                .setName("Credit Card")
                .setAmount(MoneyAmount.of(10000.0, Currency.UAH).toDbString())
        val food = CategoryRecord()
                .setName("Food")
        val taxi = CategoryRecord()
                .setName("Taxi")
        persist(wallet)
        persist(creditCard)
        persist(food)
        persist(taxi)
    }

    override fun onConfigure(db: ISQLiteDatabase) {
        super.onConfigure(db)
        db.setForeignKeyConstraintsEnabled(true)
    }

    override fun getVersion(): Int {
        return VERSION
    }

    companion object {

        private val VERSION = 1
    }
}
