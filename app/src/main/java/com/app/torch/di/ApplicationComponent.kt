package com.app.torch.di

import com.app.torch.ui.home.HomeFragment
import com.app.torch.ui.login.LoginActivity
import com.app.torch.ui.main.MainActivity
import com.app.torch.ui.register.RegisterActivity
import com.app.torch.ui.settings.SettingsFragment
import com.app.torch.ui.splash.SplashActivity
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ApplicationModule::class,
        RepositoriesModule::class,
        ViewModelModule::class,
        SharedPreferencesModule::class,
        ValidatorModule::class
    ]
)

@Singleton
interface ApplicationComponent {

    //activities
    fun inject(target: SplashActivity)
    fun inject(target: MainActivity)
    fun inject(target: LoginActivity)
    fun inject(target: RegisterActivity)


    //fragments
    fun inject(target: HomeFragment)
    fun inject(target: SettingsFragment)

}