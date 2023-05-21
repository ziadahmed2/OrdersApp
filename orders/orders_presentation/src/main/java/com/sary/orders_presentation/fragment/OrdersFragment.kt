package com.sary.orders_presentation.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.google.android.material.tabs.TabLayoutMediator
import com.sary.core_domain.util.LanguageManager
import com.sary.core_domain.util.Languages
import com.sary.core_presentation.extensions.setupToolbar
import com.sary.orders_presentation.R
import com.sary.orders_presentation.databinding.FragmentOrdersBinding
import com.sary.orders_presentation.viewpager.OrdersViewPagerAdapter

class OrdersFragment: Fragment() {
  
  private var _binding: FragmentOrdersBinding? = null
  private val binding get() = _binding!!
  
  private val languageManager by lazy { LanguageManager }
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    _binding = FragmentOrdersBinding.inflate(inflater, container, false)
    return binding.root
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupViews()
  }
  
  private fun setupViews() {
    setupToolbar()
    setupViewPager()
    setupTabLayout()
  }
  
  private val menuProvider = object: MenuProvider {
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
      menuInflater.inflate(R.menu.menu_language, menu)
    }
    
    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
      return when (menuItem.itemId) {
        R.id.english -> {
          languageManager.setLanguage(requireContext(), Languages.EN)
          requireContext().restartApp()
          true
        }
        
        R.id.arabic -> {
          languageManager.setLanguage(requireContext(), Languages.AR)
          requireContext().restartApp()
          true
        }
        
        else -> false
      }
    }
  }
  
  private fun setupToolbar() = binding.header.setupToolbar(Pair(false, "Orders")) {
    (activity as AppCompatActivity).setSupportActionBar(it.toolbar)
    activity?.actionBar?.setDisplayShowTitleEnabled(false)
    activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
    it.toolbar.setNavigationOnClickListener { activity?.finish() }
    (requireActivity() as MenuHost).addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
  }
  
  private fun setupTabLayout() {
    TabLayoutMediator(
      binding.tabLayout, binding.viewPager
    ) { tab, position ->
      when (position) {
        0 -> tab.text = "Current Orders"
        1 -> tab.text = "Previous Orders"
      }
    }.attach()
  }
  
  private fun setupViewPager() {
    val adapter = OrdersViewPagerAdapter(this)
    binding.viewPager.adapter = adapter
  }
  
  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
  
  fun Context.restartApp() {
    Toast.makeText(this, "Restarting app", Toast.LENGTH_SHORT).show()
    packageManager.getLaunchIntentForPackage(packageName)?.also {
      it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
      it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      startActivity(it)
      activity?.finish()
    }
  }
}