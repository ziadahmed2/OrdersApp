package com.sary.core_presentation.extensions

import androidx.core.view.isVisible
import com.sary.core_domain.extensions.Consumer
import com.sary.core_presentation.R
import com.sary.core_presentation.databinding.InclToolbarBinding

fun <F, S> Pair<F, S>.named(block: (F, S) -> Unit) = block(first, second)

fun InclToolbarBinding.setupToolbar(header: Pair<Boolean, String?>, toolbarSetup: Consumer<InclToolbarBinding>) {
  toolbarSetup.invoke(this)
  with(this) {
    appBarLayout.setBackgroundColor(appBarLayout.context.getColor(android.R.color.transparent))
    header.named { isHeader, toolbarTitle ->
      if (isHeader) {
        title.text = ""
      } else {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left)
        llToolbarTitles.isVisible = true
        title.text = toolbarTitle
        appBarLayout.elevation = 2F
      }
    }
    
  }
}