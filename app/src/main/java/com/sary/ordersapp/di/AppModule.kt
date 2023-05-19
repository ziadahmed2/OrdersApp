package com.sary.ordersapp.di

import android.app.Application
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.sary.core_data.preferences.DefaultPreferences
import com.sary.core_domain.preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  
  @Provides
  @Singleton
  fun provideMasterKey(app: Application): MasterKey = MasterKey.Builder(app)
    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
    .build()
  
  @Provides
  @Singleton
  fun provideSharedPreferences(app: Application, masterKey: MasterKey): SharedPreferences = EncryptedSharedPreferences.create(
    app,
    "saryPrefs",
    masterKey,
    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
  )
  
  @Provides
  @Singleton
  fun providePreferences(sharedPreferences: SharedPreferences): Preferences = DefaultPreferences(sharedPreferences)
}