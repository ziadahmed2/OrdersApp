package com.sary.ordersapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sary.core_domain.preferences.Preferences
import javax.inject.Inject

class MainActivity: AppCompatActivity() {
  
  @Inject
  lateinit var prefs: Preferences
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    
  }
}