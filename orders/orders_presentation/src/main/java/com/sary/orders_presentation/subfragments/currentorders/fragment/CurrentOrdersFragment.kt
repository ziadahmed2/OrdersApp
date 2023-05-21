package com.sary.orders_presentation.subfragments.currentorders.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sary.core_presentation.extensions.activatePagination
import com.sary.orders_presentation.subfragments.currentorders.CurrentOrdersUiState
import com.sary.orders_presentation.subfragments.currentorders.adapter.CurrentOrdersAdapter
import com.sary.orders_presentation.subfragments.currentorders.viewmodel.CurrentOrdersViewModel
import com.sary.orders_presentation.databinding.FragmentCurrentOrdersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CurrentOrdersFragment: Fragment() {
  
  private var _binding: FragmentCurrentOrdersBinding? = null
  private val binding get() = _binding!!
  
  private val currentOrdersViewModel: CurrentOrdersViewModel by viewModels()
  
  private val ordersAdapter by lazy { CurrentOrdersAdapter() }
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    _binding = FragmentCurrentOrdersBinding.inflate(inflater, container, false)
    return binding.root
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupViews()
    collectFlow()
    currentOrdersViewModel.getCurrentOrders(false)
  }
  
  private fun setupViews(){
    setupTransactionsRecyclerView()
  }
  
  private fun collectFlow(){
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        currentOrdersViewModel.userFlow.collect {
          render(it)
        }
      }
    }
  }
  
  private fun render(uiState: CurrentOrdersUiState) {
    when (uiState) {
      is CurrentOrdersUiState.Loading -> binding.srOrders.isRefreshing = true
      is CurrentOrdersUiState.Success -> setAdapterData(uiState.currentOrdersResult)
      is CurrentOrdersUiState.Error -> TODO()
    }
  }
  
  private fun setupTransactionsRecyclerView() = binding.rvOrders.apply {
    itemAnimator = null
    activatePagination {
      currentOrdersViewModel.getCurrentOrders(false)
    }
    
    adapter = ordersAdapter
    
    binding.srOrders.setOnRefreshListener {
      setFirstTimeOrders()
    }
  }
  
  private fun setAdapterData(orders: List<Any>) {
    binding.srOrders.isRefreshing = false
    binding.rvOrders.isVisible = true
    ordersAdapter.setData(orders)
  }
  
  private fun setFirstTimeOrders() = currentOrdersViewModel.getCurrentOrders(reset = true)
  
  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}