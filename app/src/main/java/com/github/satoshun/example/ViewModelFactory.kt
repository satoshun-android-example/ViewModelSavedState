package com.github.satoshun.example

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
  owner: FragmentActivity,
  private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<SavedStateVMFactory>>
) : AbstractSavedStateViewModelFactory(owner, owner.intent.extras) {
  override fun <T : ViewModel> create(
    key: String,
    modelClass: Class<T>,
    handle: SavedStateHandle
  ): T {
    var creator: Provider<out SavedStateVMFactory>? = creators[modelClass]
    if (creator == null) {
      for ((key, value) in creators) {
        if (modelClass.isAssignableFrom(key)) {
          creator = value
          break
        }
      }
    }
    if (creator == null) {
      throw IllegalArgumentException("unknown model class $modelClass")
    }
    try {
      @Suppress("UNCHECKED_CAST")
      return creator.get().create(handle) as T
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }
}

interface SavedStateVMFactory {
  fun create(
    state: SavedStateHandle
  ): ViewModel
}
