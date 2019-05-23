package com.github.satoshun.example

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AbstractSavedStateVMFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

fun viewModelWrapper(
  owner: FragmentActivity,
  body: (state: SavedStateHandle) -> SavedStateViewModel3
): AbstractSavedStateVMFactory {
  return object : AbstractSavedStateVMFactory(owner, owner.intent.extras) {
    override fun <T : ViewModel> create(
      key: String,
      modelClass: Class<T>,
      handle: SavedStateHandle
    ): T {
      @Suppress("UNCHECKED_CAST")
      return body(handle) as T
    }
  }
}
