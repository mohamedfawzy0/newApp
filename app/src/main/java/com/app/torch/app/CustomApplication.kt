package com.app.torch.app

import android.app.Application
import android.content.Context
import com.app.torch.di.ApplicationComponent
import com.app.torch.di.ApplicationModule
import com.app.torch.di.DaggerApplicationComponent
import com.app.torch.utils.interceptors.CustomTextViewInterceptor
import com.app.torch.utils.interceptors.TextUpdatingInterceptor
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump


class CustomApplication : Application() {

    lateinit var component: ApplicationComponent
        private set

    companion object {
        lateinit var application: CustomApplication
            private set
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        application = this

        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("Fonts/cairo_regular.ttf")
                            .setFontAttrId(com.app.torch.R.attr.fontPath)
                            .build()
                    )
                )
                .addInterceptor(TextUpdatingInterceptor())
                .addInterceptor(CustomTextViewInterceptor())
                .build()
        )
    }
}