package com.sary.orders_presentation.previousorders.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sary.orders_presentation.databinding.FragmentPreviousOrdersBinding

class PreviousOrdersFragment: Fragment() {
  
  private var _binding: FragmentPreviousOrdersBinding? = null
  private val binding get() = _binding!!
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    _binding = FragmentPreviousOrdersBinding.inflate(inflater, container, false)
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