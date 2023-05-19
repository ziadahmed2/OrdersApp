package com.sary.core_domain.preferences

interface Preferences {
  
  fun saveLanguage(language: String)
  fun getLanguage(): String?
  
  companion object Language {
    
    const val AR = "ar"
    const val EN = "en"
  }
}