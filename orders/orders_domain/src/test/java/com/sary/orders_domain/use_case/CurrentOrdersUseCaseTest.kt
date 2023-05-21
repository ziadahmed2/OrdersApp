package com.sary.orders_domain.use_case

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CurrentOrdersUseCaseTest {
  private lateinit var currentOrdersUseCase: CurrentOrdersUseCase
  private lateinit var pastOrdersUseCase: PastOrdersUseCase
  private lateinit var fakeOrdersRepository: FakeOrdersRepository
  
  @Before
  fun setUp(){
    fakeOrdersRepository = FakeOrdersRepository()
    currentOrdersUseCase = CurrentOrdersUseCase(fakeOrdersRepository)
    pastOrdersUseCase = PastOrdersUseCase(fakeOrdersRepository)
  }
  
  @Test
  fun `Get Current Orders List, correct order list return` (): Unit = runBlocking{
    val orders = currentOrdersUseCase.invoke(1).first()
    assertThat(orders.data?.orders?.get(0)?.shipments?.get(0)?.shipmentCode == "123").isTrue()
  }
  
  @Test
  fun `Get Current Orders List, incorrect order list return` (): Unit = runBlocking{
    val orders = currentOrdersUseCase(1).first()
    assertThat(orders.data?.orders?.get(0)?.shipments?.get(0)?.shipmentCode == "1").isFalse()
  }
  
  @Test
  fun `Get Past Orders List, correct order list return` (): Unit = runBlocking{
    val orders = pastOrdersUseCase.invoke(1).first()
    assertThat(orders.data?.orders?.get(0)?.cartTotal == "500").isTrue()
  }
  
  @Test
  fun `Get Past Orders List, incorrect order list return` (): Unit = runBlocking{
    val orders = pastOrdersUseCase(1).first()
    assertThat(orders.data?.orders?.get(0)?.cartTotal == "1").isFalse()
  }
}