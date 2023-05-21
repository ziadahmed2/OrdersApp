package com.sary.core_data.preferences

import android.content.SharedPreferences
import com.sary.core_data.extensions.get
import com.sary.core_data.extensions.put
import com.sary.core_domain.preferences.Preferences

class DefaultPreferences(
  private val sharedPref: SharedPreferences
): Preferences {
  
  override fun saveLanguage(language: String) = sharedPref.put(LANG, language)
  override fun getLanguage(): String? = sharedPref.get(LANG, Preferences.Language.EN)
  
  companion object {
    const val LANG = "lang"
  }
}