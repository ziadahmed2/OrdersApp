package com.sary.orders_presentation.subfragments.previousorders.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sary.core_presentation.extensions.toggleActivation
import com.sary.orders_domain.model.past.PastOrderResult
import com.sary.orders_presentation.databinding.ItemPastOrderBinding
import com.sary.orders_presentation.subfragments.currentorders.adapter.ProductImageAdapter

class PastOrdersAdapter: RecyclerView.Adapter<PastOrdersAdapter.PastOrdersViewHolder>() {
  
  private var items: List<PastOrderResult> = listOf()
  
  inner class PastOrdersViewHolder(
    private val binding: ItemPastOrderBinding,
  ): RecyclerView.ViewHolder(binding.root) {
    
    fun bind(result: PastOrderResult) {
      with(binding) {
        tvOrderNumber.text = "Order number #${result.orderInfo?.orderId}"
        tvOrderDate.text = "Arrives: ${result.orderInfo?.orderDate} at ${result.orderInfo?.orderTime}"
        tvOrderStatus.text = result.orderInfo?.orderStatus
        tvOrderAmount.text = "SAR ${result.cartTotal}"
        btnRate.toggleActivation(result.canRate ?: false)
        btnRate.text = if (result.canRate == true) "Rate" else "Already rated"
        rvProductImages.apply {
          adapter = ProductImageAdapter().apply {
            isNestedScrollingEnabled = false
            val images = result.items?.map { it.image ?: "" } ?: emptyList()
            setData(if (images.size > 3) images.subList(0, 2) else images)
          }
        }
      }
    }
  }
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastOrdersViewHolder =
    PastOrdersViewHolder(ItemPastOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
  
  override fun onBindViewHolder(holder: PastOrdersViewHolder, position: Int) {
    val item = items[position]
    holder.bind(item)
  }
  
  override fun getItemCount(): Int = items.size
  
  fun setData(results: List<PastOrderResult>) {
    items = results
    notifyItemRangeChanged(0, results.size)
  }
}