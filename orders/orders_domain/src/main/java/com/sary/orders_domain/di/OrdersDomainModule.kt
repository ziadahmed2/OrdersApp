package com.sary.orders_domain.di

import com.sary.orders_domain.repository.OrdersRepo
import com.sary.orders_domain.use_case.CurrentOrdersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OrdersDomainModule {
  
  @ViewModelScoped
  @Provides
  fun provideCurrentOrdersUseCase(repository: OrdersRepo): CurrentOrdersUseCase {
    return CurrentOrdersUseCase(ordersRepo = repository)
  }
}