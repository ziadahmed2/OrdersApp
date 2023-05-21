package com.sary.core_presentation.extensions

import androidx.annotation.ColorRes
import com.google.android.material.button.MaterialButton
import com.sary.core_presentation.R

fun MaterialButton.toggleActivation(
  isActive: Boolean,
  @ColorRes activatedTextColor: Int = R.color.accent_base
) {
  isEnabled = if (isActive) {
    setTextColor(context.getColor(activatedTextColor))
    true
  } else {
    setTextColor(context.getColor(R.color.neutral_05))
    false
  }
}