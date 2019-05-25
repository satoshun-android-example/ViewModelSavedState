package com.github.satoshun.example

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AbstractSavedStateVMFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Provider

class SavedStateViewModel4(
  private val state: SavedStateHandle,
  private val dummy: Dummy
) : ViewModel() {

  init {
    Log.d("construct4", (state.get("test") as String?).toString())
  }

  fun write() {
    state.set("test", "test4")
  }
}

interface SavedStateViewModelFactory {
  fun create(
    handle: SavedStateHandle
  ): ViewModel
}

class ViewModelFactory @Inject constructor(
  owner: FragmentActivity,
  private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<SavedStateViewModelFactory>>
) : AbstractSavedStateVMFactory(owner, owner.intent.extras) {
  override fun <T : ViewModel> create(
    key: String,
    modelClass: Class<T>,
    handle: SavedStateHandle
  ): T {
    var creator: Provider<out SavedStateViewModelFactory>? = creators[modelClass]
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
