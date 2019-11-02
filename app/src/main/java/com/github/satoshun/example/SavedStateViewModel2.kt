package com.github.satoshun.example

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SavedStateViewModel2(
  private val dummy: Dummy,
  private val state: SavedStateHandle
) : ViewModel() {
  class Factory @Inject constructor(private val dummy: Dummy) {
    fun create(owner: FragmentActivity): AbstractSavedStateViewModelFactory {
      return object : AbstractSavedStateViewModelFactory(owner, owner.intent.extras) {
        override fun <T : ViewModel> create(
          key: String,
          modelClass: Class<T>,
          handle: SavedStateHandle
        ): T {
          @Suppress("UNCHECKED_CAST")
          return SavedStateViewModel2(dummy, handle) as T
        }
      }
    }
  }

  init {
    Log.d("construct2", (state.get("test") as String?).toString())
  }

  fun write() {
    state.set("test", "test2")
  }
}
