package com.app.torch.di


import com.app.torch.utils.managers.Validator
import com.app.torch.utils.managers.ValidatorInterface
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ValidatorModule {

    @Binds
    @Singleton
    abstract fun bindValidator(validator: Validator): ValidatorInterface
}