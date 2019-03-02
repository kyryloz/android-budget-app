package com.robotnec.budget.refactored.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robotnec.budget.core.persistence.dao.AccountDao
import com.robotnec.budget.core.persistence.dao.TransactionDao
import com.robotnec.budget.core.service.aggregation.AggregationService
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        private val accountDao: AccountDao,
        private val transactionDao: TransactionDao,
        private val aggregationService: AggregationService
) : ViewModel() {

    fun getAccounts() = MutableLiveData(accountDao.all)

    fun getAggregatedTransactions() = MutableLiveData(
            aggregationService.aggregate(
                    transactionDao.all,
                    AggregationService.Resolution.DAY
            )
    )

}