package com.sary.core_domain.util

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.util.DisplayMetrics
import java.util.*

object LanguageManager {
  
  fun attach(context: Context): Context {
    val language = getLanguage(context)
    return setLocale(context, language)
  }
  
  fun attach(context: Context, defaultLanguage: String): Context {
    val language = getLanguage(context, defaultLanguage)
    return setLocale(context, language)
  }
  
  private fun getLanguage(context: Context, defaultLanguage: String): String {
    return getLanguageSharedPreferences(context).getString(LanguagePrefs.CURRENT_LANGUAGE, defaultLanguage) ?: Languages.EN
  }
  
  fun getLanguage(context: Context): String {
    return getLanguageSharedPreferences(context).getString(LanguagePrefs.CURRENT_LANGUAGE, Languages.EN) ?: Languages.EN
  }
  
  fun getLanguageIndex(context: Context): Int {
    return when (getLanguage(context)) {
      Languages.AR -> 1
      else -> 0
    }
  }
  
  fun setLanguage(context: Context, language: String) {
    getLanguageSharedPreferences(context).edit().putString(LanguagePrefs.CURRENT_LANGUAGE, language).apply()
  }
  
  private fun getLanguageSharedPreferences(context: Context): SharedPreferences =
    context.getSharedPreferences(LanguagePrefs.LANGUAGE, Context.MODE_PRIVATE)
  
  private fun setLocale(context: Context, language: String): Context {
    setLanguage(context, language)
    return if (VERSION.SDK_INT >= VERSION_CODES.N) {
      updateResources(context, language)
    } else updateResourcesLegacy(context, language)
  }
  
  @Suppress("DEPRECATION")
  @TargetApi(VERSION_CODES.N)
  private fun updateResources(
    context: Context,
    language: String
  ): Context {
    val locale = createLocale(language)
    val resources: Resources = context.resources
    val dm: DisplayMetrics = resources.displayMetrics
    val configuration: Configuration = resources.configuration
    configuration.locale = locale
    configuration.setLayoutDirection(locale)
    resources.updateConfiguration(configuration, dm)
    return context.createConfigurationContext(configuration)
  }
  
  @Suppress("DEPRECATION")
  private fun updateResourcesLegacy(context: Context, language: String): Context {
    val locale = createLocale(language)
    Locale.setDefault(locale)
    val resources = context.resources
    val configuration = resources.configuration
    configuration.locale = locale
    configuration.setLayoutDirection(locale)
    resources.updateConfiguration(configuration, resources.displayMetrics)
    return context
  }
  
  private fun createLocale(language: String): Locale = Locale(language)
}

object LanguagePrefs {
  
  const val LANGUAGE = "language"
  const val CURRENT_LANGUAGE = "currentLanguage"
}

object Languages {
  
  const val AR = "ar"
  const val EN = "en"
}