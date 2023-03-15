package com.app.torch.di

import com.app.torch.utils.managers.SharedPreferencesManager
import com.app.torch.utils.managers.SharedPreferencesManagerInterface
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class])
abstract class SharedPreferencesModule {

    @Binds
    @Singleton
    abstract fun bindSharedPreferences(sharedPreferencesManager: SharedPreferencesManager): SharedPreferencesManagerInterface
}