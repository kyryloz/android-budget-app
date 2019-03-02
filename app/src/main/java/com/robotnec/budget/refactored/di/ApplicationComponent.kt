package com.robotnec.budget.refactored.di

import android.app.Application
import com.robotnec.budget.refactored.BudgetApplication
import com.robotnec.budget.core.mvp.presenter.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidComponentsInjectionModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<BudgetApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(presenter: AccountsPresenter)

    fun inject(presenter: AddTransactionPresenter)

    fun inject(presenter: MainPresenter)

    fun inject(presenter: CategoriesPresenter)

    fun inject(presenter: TransactionsPresenter)

    fun inject(presenter: AboutPresenter)

    fun inject(presenter: AddCategoryPresenter)

    fun inject(presenter: AddAccountPresenter)

    fun inject(presenter: CategoryOverviewPresenter)

    fun inject(presenter: CalculatorPresenter)
}