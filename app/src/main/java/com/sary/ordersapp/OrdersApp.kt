package com.sary.ordersapp

import android.app.Application
import android.content.Context
import com.sary.core_domain.util.LanguageManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OrdersApp : Application(){
  
  override fun attachBaseContext(base: Context) {
    super.attachBaseContext(LanguageManager.attach(base, LanguageManager.getLanguage(base)))
  }
}