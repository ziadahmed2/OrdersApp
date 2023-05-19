package com.sary.orders_presentation.currentorders.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sary.orders_presentation.databinding.FragmentCurrentOrdersBinding

class CurrentOrdersFragment: Fragment() {
  
  private var _binding: FragmentCurrentOrdersBinding? = null
  private val binding get() = _binding!!
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    _binding = FragmentCurrentOrdersBinding.inflate(inflater, container, false)
    return binding.root
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }
  
  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}