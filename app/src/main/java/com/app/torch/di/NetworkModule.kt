package com.app.torch.di

import android.content.Context
import android.content.res.Resources
import com.app.torch.BuildConfig
import com.app.torch.utils.interceptors.CurlLoggingInterceptor
import com.app.torch.utils.interceptors.HeadersInterceptor
import com.app.torch.utils.interceptors.HttpLoggingInterceptor
import com.app.torch.utils.interceptors.ResponseLoggingInterceptor
import com.app.torch.utils.managers.*
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class, SharedPreferencesModule::class])
class NetworkModule {

    @Provides
    @Singleton
    fun provideInternetConnectionManager(context: Context): InternetConnectionManagerInterface {
        return InternetConnectionManager(context)
    }

    @Provides
    @Singleton
    fun provideApiRequestManager(resources: Resources): ApiRequestManagerInterface {
        return ApiRequestManager(resources)
    }

    @Provides
    @Singleton
    @Named("baseURL")
    fun provideBaseURL(): String {
        return "${BuildConfig.HOST}${BuildConfig.BASE_URL}"
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(sharedPreferencesManager: SharedPreferencesManagerInterface): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HeadersInterceptor(sharedPreferencesManager))

        if (BuildConfig.DEBUG) {
            var curl = CurlLoggingInterceptor()
            var responseLogging = ResponseLoggingInterceptor()
            okHttpClientBuilder
                .addInterceptor(curl)
                .addInterceptor(responseLogging)

            val loggingInterceptor = HttpLoggingInterceptor()
            val loggingInterceptor2 = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            loggingInterceptor2.level = HttpLoggingInterceptor.Level.HEADERS

            okHttpClientBuilder.addInterceptor(loggingInterceptor)
                .addInterceptor(loggingInterceptor2)
        }

        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(
        @Named("baseURL") baseUrl: String,
        gson: Gson,
        httpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClient)
            .build()
    }
}
