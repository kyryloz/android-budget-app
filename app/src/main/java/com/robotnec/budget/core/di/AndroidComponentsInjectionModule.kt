package com.robotnec.budget.core.di

import com.robotnec.budget.core.di.module.AppModule
import dagger.Module

@Suppress("unused")
@Module(
    includes = [
        AppModule::class
    ]
)
abstract class AndroidComponentsInjectionModule {

//    @ContributesAndroidInjector
//    abstract fun contributeInboxFragmentInjector(): InboxFragment
//
//    @ContributesAndroidInjector
//    abstract fun contributeOrganizerFragmentInjector(): OrganizerFragment
//
//    @ContributesAndroidInjector
//    abstract fun contributeCreateTodoFragmentInjector(): CreateTodoFragment
//
//    @ContributesAndroidInjector
//    abstract fun contributeCreateFolderFragmentInjector(): CreateFolderFragment
//
//    @ContributesAndroidInjector
//    abstract fun contributeItemBottomMenuDialogFragmentInjector(): ItemBottomMenuDialogFragment
}