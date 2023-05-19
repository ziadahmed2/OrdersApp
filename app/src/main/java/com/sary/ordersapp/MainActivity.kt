package com.sary.ordersapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sary.core_domain.preferences.Preferences
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
  
  @Inject
  lateinit var prefs: Preferences
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    CoroutineScope(Dispatchers.Main).launch {
      delay(3000)
      Log.d("TAG", "onCreate: ${prefs.getLanguage()}")
    }
    
  }
}