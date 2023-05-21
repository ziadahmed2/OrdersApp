package com.sary.orders_data.repository

import com.sary.core_domain.util.Resource
import com.sary.orders_data.mappers.toDomain
import com.sary.orders_data.remote.SaryApi
import com.sary.orders_domain.model.CurrentOrders
import com.sary.orders_domain.repository.OrdersRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class OrdersRepoImpl(private val api: SaryApi): OrdersRepo {
  
  override suspend fun getCurrentOrders(page: Int): Flow<Resource<CurrentOrders>> {
    return flow<Resource<CurrentOrders>> {
      emit(Resource.Loading(true))
      
      val response = try {
        val response = api.getCurrentOrders(page = page)
        response
      } catch (e: IOException) {
        e.printStackTrace()
        emit(Resource.Error(message = e.localizedMessage ?: "", isConnectionError = true))
        null
      } catch (e: HttpException) {
        e.printStackTrace()
        emit(Resource.Error(message = e.localizedMessage ?: "", isConnectionError = false))
        null
      }
      
      response?.let { orderResults ->
        emit(Resource.Success(data = orderResults.toDomain()))
      }
    }
  }
}