package com.sary.core_presentation.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sary.core_domain.extensions.Action

private var loading = true
private var pastVisiblesItems = 1
private var visibleItemCount: Int = 0
private var totalItemCount: Int = 0

fun RecyclerView.activatePagination(callback: Action) =
  addOnScrollListener(object: RecyclerView.OnScrollListener() {
    
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
      if (dy > 0) {
        val mLayoutManager = layoutManager as LinearLayoutManager
        visibleItemCount = mLayoutManager.childCount
        totalItemCount = mLayoutManager.itemCount
        pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition()
        if (loading) {
          if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
            loading = false
            callback()
            loading = true
          }
        }
      }
    }
  })