package com.app.torch.utils

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.net.Uri
import android.os.Build
import android.preference.PreferenceManager
import android.provider.MediaStore
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import java.util.*

object LocaleHelper {
    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
    fun onAttach(context: Context): Context {
        val lang = getPersistedLanguage(context, Locale.getDefault().language)
        return setLocale(context, lang)
    }
    fun onAttach(context: Context, defaultLanguage: String): Context {
        val lang = getPersistedLanguage(context, defaultLanguage)
        return setLocale(context, lang)
    }
    fun getLanguage(context: Context): String? {
        return getPersistedLanguage(context, Locale.getDefault().language)
    }
    fun setLocale(context: Context, language: String?): Context {
        persist(context, language)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ViewPumpContextWrapper.wrap(updateResources(context, language))
        } else {
            ViewPumpContextWrapper.wrap(updateResourcesLegacy(context, language))
        }
    }
    private fun getPersistedLanguage(context: Context, defaultLanguage: String): String? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage)
    }
    private fun persist(context: Context, language: String?) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferences.edit()
        editor.putString(SELECTED_LANGUAGE, language)
        editor.apply()
    }
    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String?): Context {
        val locale = Locale(language)
        val configuration: Configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }
    private fun updateResourcesLegacy(context: Context, language: String?): Context {
        val locale = Locale(language)
        val resources: Resources = context.resources
        val configuration: Configuration = resources.getConfiguration()
        configuration.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale)
        }
        resources.updateConfiguration(configuration, resources.getDisplayMetrics())
        return context
    }
    fun getRealPathFromURI(context: Context, uri: Uri): String {
        val cursor = context.contentResolver.query(uri, null, null, null, null)!!
        cursor.moveToFirst()
        val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        val str = cursor.getString(idx)
        cursor.close()
        return str
    }
}