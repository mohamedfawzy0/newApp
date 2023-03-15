package com.app.torch.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import com.app.torch.utils.managers.RuntimePermissionsManager
import com.app.torch.utils.managers.RuntimePermissionsManagerInterface
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideResources(): Resources {
        val conf = application.resources.configuration
        conf.locale = Locale(Locale.getDefault().language)
        val metrics = DisplayMetrics()
        return Resources(application.assets, metrics, conf)
    }

    @Provides
    @Singleton
    fun provideRuntimePermissionManager(): RuntimePermissionsManagerInterface {
        return RuntimePermissionsManager(application)
    }
}