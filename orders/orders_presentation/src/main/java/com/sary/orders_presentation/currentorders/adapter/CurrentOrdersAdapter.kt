package com.sary.orders_presentation.currentorders.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sary.orders_domain.model.OrderInfo
import com.sary.orders_domain.model.Shipment
import com.sary.orders_presentation.databinding.ItemCurrentOrderFooterBinding
import com.sary.orders_presentation.databinding.ItemCurrentOrderHeaderBinding
import com.sary.orders_presentation.databinding.ItemCurrentOrderShipmentBinding
import java.util.*

class CurrentOrdersAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  
  private val diffCallback = object: DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
      return oldItem.hashCode() == newItem.hashCode()
    }
    
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
      return oldItem.hashCode() == newItem.hashCode()
    }
  }
  
  private val differ = AsyncListDiffer(this, diffCallback)
  
  inner class CurrentOrderHeaderViewHolder(private val binding: ItemCurrentOrderHeaderBinding):
    RecyclerView.ViewHolder(binding.root) {
    
    fun bind(item: OrderInfo?) {
      with(binding) {
        item?.let {
          tvOrderNumber.text = "Order number ${it.orderId}"
          tvOrderStatus.text = "Ordered: ${it.orderDate} at ${it.orderTime}"
        }
      }
    }
  }
  
  inner class CurrentOrderShipmentViewHolder(
    private val binding: ItemCurrentOrderShipmentBinding):
    RecyclerView.ViewHolder(binding.root) {
    
    fun bind(item: Shipment?) {
      with(binding) {
        tvShipmentNumber.text = "Shipment ${item?.shipmentCode}"
        tvShipmentStatus.text = item?.deliveryCurrentStatus
        tvShipmentDate.text = "${item?.deliveryDate}, ${item?.intervalStartTime12} - ${item?.intervalEndTime12}"
        rvProductImages.apply {
          adapter = ProductImageAdapter().apply {
            isNestedScrollingEnabled = false
            val images = item?.items?.map { it.image ?: "" } ?: emptyList()
            setData(if (images.size > 3) images.subList(0, 2) else images)
          }
        }
      }
    }
  }
  
  inner class CurrentOrderFooterViewHolder(
    binding: ItemCurrentOrderFooterBinding):
    RecyclerView.ViewHolder(binding.root) {
    
    fun bind() {}
  }
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return when (viewType) {
      CurrentOrderViewTypes.HEADER -> CurrentOrderHeaderViewHolder(
        ItemCurrentOrderHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      )
      
      CurrentOrderViewTypes.SHIPMENT -> CurrentOrderShipmentViewHolder(
        ItemCurrentOrderShipmentBinding.inflate(LayoutInflater.from(parent.context), parent, false),
      )
      
      else -> CurrentOrderFooterViewHolder(
        ItemCurrentOrderFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      )
      
    }
  }
  
  override fun getItemViewType(position: Int): Int = when (differ.currentList[position]) {
    is OrderInfo -> CurrentOrderViewTypes.HEADER
    is Shipment -> CurrentOrderViewTypes.SHIPMENT
    else -> CurrentOrderViewTypes.FOOTER
  }
  
  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val item = differ.currentList[position]
    when (holder) {
      is CurrentOrderHeaderViewHolder -> holder.bind(item as OrderInfo?)
      is CurrentOrderShipmentViewHolder -> holder.bind(item as Shipment?)
      is CurrentOrderFooterViewHolder -> holder.bind()
    }
  }
  
  override fun getItemCount(): Int = differ.currentList.size
  
  fun setData(orderList: List<Any>) {
    differ.submitList(orderList)
  }
  
  object CurrentOrderViewTypes {
    
    const val HEADER = 0
    const val FOOTER = 1
    const val SHIPMENT = 2
  }
}