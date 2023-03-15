package com.app.torch.di

import com.app.torch.repositories.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
abstract class RepositoriesModule {

    @Binds
    @Singleton
    abstract fun bindAuthenticationRepo(repo: AuthenticationRepo): AuthenticationRepoInterface

    @Binds
    @Singleton
    abstract fun bindMainRepo(repo: MainRepo): MainRepoInterface

    @Binds
    @Singleton
    abstract fun bindHomeRepo(repo: HomeRepo): HomeRepoInterface

}