package com.sary.orders_presentation.subfragments.previousorders.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sary.core_presentation.extensions.activatePagination
import com.sary.orders_domain.model.past.PastOrderResult
import com.sary.orders_presentation.databinding.FragmentPreviousOrdersBinding
import com.sary.orders_presentation.subfragments.previousorders.PreviousOrdersUiState
import com.sary.orders_presentation.subfragments.previousorders.adapter.PastOrdersAdapter
import com.sary.orders_presentation.subfragments.previousorders.viewmodel.PastOrdersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PreviousOrdersFragment: Fragment() {
  
  private var _binding: FragmentPreviousOrdersBinding? = null
  private val binding get() = _binding!!
  
  private val pastOrdersViewModel: PastOrdersViewModel by viewModels()
  
  private val ordersAdapter by lazy { PastOrdersAdapter() }
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    _binding = FragmentPreviousOrdersBinding.inflate(inflater, container, false)
    return binding.root
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupViews()
    collectFlow()
    pastOrdersViewModel.getPastOrders(false)
  }
  
  private fun setupViews() {
    setupOrdersRecyclerView()
  }
  
  private fun collectFlow() {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        pastOrdersViewModel.userFlow.collect {
          render(it)
        }
      }
    }
  }
  
  private fun render(uiState: PreviousOrdersUiState) {
    when (uiState) {
      is PreviousOrdersUiState.FirstLoading -> setFirstLoading(true)
      is PreviousOrdersUiState.Loading -> binding.srPrevOrders.isRefreshing = true
      is PreviousOrdersUiState.Success -> setAdapterData(uiState.previousOrderResults)
      is PreviousOrdersUiState.Error -> Toast.makeText(
        requireContext(),
        uiState.uiText.asString(requireContext()),
        Toast.LENGTH_LONG
      ).show().also {
        setFirstLoading(false)
        binding.srPrevOrders.isRefreshing = false
      }
    }
  }
  
  private fun setFirstLoading(loading: Boolean) {
    binding.inclShimmer.root.apply {
      isVisible = loading
      if (loading) startShimmer() else stopShimmer()
    }
    
    binding.rvPrevOrders.isVisible = false
  }
  
  private fun setupOrdersRecyclerView() = binding.rvPrevOrders.apply {
    itemAnimator = null
    activatePagination {
      pastOrdersViewModel.getPastOrders(false)
    }
    
    adapter = ordersAdapter
    
    binding.srPrevOrders.setOnRefreshListener {
      setFirstTimeOrders()
    }
  }
  
  private fun setAdapterData(orders: List<PastOrderResult>) {
    setFirstLoading(false)
    binding.srPrevOrders.isRefreshing = false
    binding.rvPrevOrders.isVisible = true
    ordersAdapter.setData(orders)
  }
  
  private fun setFirstTimeOrders() = pastOrdersViewModel.getPastOrders(reset = true)
  
  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}