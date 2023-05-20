package com.sary.orders_presentation.currentorders.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sary.orders_presentation.databinding.ItemProductImageBinding

class ProductImageAdapter: RecyclerView.Adapter<ProductImageAdapter.ProductImageViewHolder>() {
  
  private var items: List<String> = listOf()
  
  inner class ProductImageViewHolder(
    private val binding: ItemProductImageBinding,
  ): RecyclerView.ViewHolder(binding.root) {
    
    fun bind(image: String) {
      with(binding) {
        Glide.with(root.context).load(image).into(ivProduct)
      }
    }
  }
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImageViewHolder =
    ProductImageViewHolder(ItemProductImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
  
  override fun onBindViewHolder(holder: ProductImageViewHolder, position: Int) {
    val item = items[position]
    holder.bind(item)
  }
  
  override fun getItemCount(): Int = items.size
  
  fun setData(images: List<String>) {
    items = images
    notifyItemRangeChanged(0, images.size)
  }
}