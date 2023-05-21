package com.sary.orders_presentation.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sary.orders_presentation.subfragments.currentorders.fragment.CurrentOrdersFragment
import com.sary.orders_presentation.subfragments.previousorders.fragment.PreviousOrdersFragment

class OrdersViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
  
  override fun getItemCount(): Int = 2
  
  override fun createFragment(position: Int): Fragment {
    return when (position) {
      0 -> CurrentOrdersFragment()
      1 -> PreviousOrdersFragment()
      else -> CurrentOrdersFragment()
    }
  }
}