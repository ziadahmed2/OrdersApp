package com.sary.orders_data.di

import android.content.Context
import com.sary.core_data.extensions.isInternetAvailable
import com.sary.core_domain.util.Exceptions
import com.sary.core_domain.util.LanguageManager
import com.sary.orders_data.remote.SaryApi
import com.sary.orders_data.repository.OrdersRepoImpl
import com.sary.orders_domain.repository.OrdersRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrdersDataModule {
  
  @Singleton
  @Provides
  fun providesNetworkInterceptor(@ApplicationContext context: Context): Interceptor = Interceptor { chain ->
    val request = chain.request().newBuilder()
      .addHeader("Authorization", "token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjgyNDA2LCJ1c2VyX3Bob25lIjoiOTY2NTEyMTIxMjM0In0.7Taumv67S0xEv2P5K8F9JdJMYAferY45ZuNghUXtPW4")
      .addHeader("App-Version", "7.7.0.0.0")
      .addHeader("Accept-Language", LanguageManager.getLanguage(context))
      .addHeader("Device-Type", "android")
      .addHeader("Platform", "FLAGSHIP")
      .build()
    if (context.isInternetAvailable().not()) throw Exceptions.NetworkNotAvailableException()
    chain.proceed(request)
  }
  
  @Singleton
  @Provides
  fun providesOkHttpClient(networkInterceptor: Interceptor): OkHttpClient =
    OkHttpClient.Builder()
      .connectTimeout(60, TimeUnit.SECONDS)
      .readTimeout(60, TimeUnit.SECONDS)
      .writeTimeout(60, TimeUnit.SECONDS)
      .addInterceptor(networkInterceptor)
      .addInterceptor(
        HttpLoggingInterceptor().apply {
          level = HttpLoggingInterceptor.Level.BODY
        }
      )
      .build()
  
  @Provides
  @Singleton
  fun provideSaryApi(client: OkHttpClient): SaryApi {
    return Retrofit.Builder()
      .baseUrl("https://staging.sary.to/api/")
      .addConverterFactory(GsonConverterFactory.create())
      .client(client)
      .build()
      .create()
  }
  
  @Provides
  @Singleton
  fun provideOrdersRepo(api: SaryApi): OrdersRepo {
    return OrdersRepoImpl(api = api)
  }
}