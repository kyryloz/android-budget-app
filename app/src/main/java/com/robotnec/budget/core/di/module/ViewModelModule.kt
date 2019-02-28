package com.robotnec.budget.core.di.module

import androidx.lifecycle.ViewModel
import com.robotnec.budget.core.di.helpers.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @[Binds IntoMap ViewModelKey(CreateTodoViewModel::class)]
    abstract fun bindCreateTodoViewModel(factory: CreateTodoViewModel): ViewModel
//
//    @[Binds IntoMap ViewModelKey(InboxViewModel::class)]
//    abstract fun bindInboxViewModel(factory: InboxViewModel): ViewModel
//
//    @[Binds IntoMap ViewModelKey(OrganizerViewModel::class)]
//    abstract fun bindOrganizerViewModel(factory: OrganizerViewModel): ViewModel
}

class CreateTodoViewModel : ViewModel()