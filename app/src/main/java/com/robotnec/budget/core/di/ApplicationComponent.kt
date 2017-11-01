package com.robotnec.budget.core.di

import com.robotnec.budget.core.di.module.AndroidModule
import com.robotnec.budget.core.di.module.DaoModule
import com.robotnec.budget.core.di.module.NavigationModule
import com.robotnec.budget.core.di.module.ServiceModule
import com.robotnec.budget.core.mvp.presenter.AboutPresenter
import com.robotnec.budget.core.mvp.presenter.AccountsPresenter
import com.robotnec.budget.core.mvp.presenter.AddAccountPresenter
import com.robotnec.budget.core.mvp.presenter.AddCategoryPresenter
import com.robotnec.budget.core.mvp.presenter.AddTransactionPresenter
import com.robotnec.budget.core.mvp.presenter.CalculatorPresenter
import com.robotnec.budget.core.mvp.presenter.CategoriesPresenter
import com.robotnec.budget.core.mvp.presenter.CategoryOverviewPresenter
import com.robotnec.budget.core.mvp.presenter.HomePresenter
import com.robotnec.budget.core.mvp.presenter.MainPresenter
import com.robotnec.budget.core.mvp.presenter.TransactionsPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * @author zak zak@swingpulse.com>
 */
@Singleton
@Component(modules = arrayOf(
        DaoModule::class,
        NavigationModule::class,
        AndroidModule::class,
        ServiceModule::class)
)
interface ApplicationComponent {
    fun inject(presenter: AccountsPresenter)

    fun inject(presenter: AddTransactionPresenter)

    fun inject(presenter: MainPresenter)

    fun inject(presenter: HomePresenter)

    fun inject(presenter: CategoriesPresenter)

    fun inject(presenter: TransactionsPresenter)

    fun inject(presenter: AboutPresenter)

    fun inject(presenter: AddCategoryPresenter)

    fun inject(presenter: AddAccountPresenter)

    fun inject(presenter: CategoryOverviewPresenter)

    fun inject(presenter: CalculatorPresenter)
}
