package com.sary.orders_domain.use_case

import com.sary.core_domain.util.Resource
import com.sary.orders_domain.model.past.PastOrders
import com.sary.orders_domain.repository.OrdersRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PastOrdersUseCase @Inject constructor(
  private val ordersRepo: OrdersRepo
) {
  
  suspend operator fun invoke(page: Int): Flow<Resource<PastOrders>> {
    return ordersRepo.getPastOrders(page)
  }
}