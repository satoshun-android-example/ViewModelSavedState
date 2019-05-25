package com.github.satoshun.example

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class SavedStateViewModel4 @AssistedInject constructor(
  @Assisted private val state: SavedStateHandle,
  private val dummy: Dummy
) : ViewModel() {

  @AssistedInject.Factory
  interface Factory : SavedStateViewModelFactory {
    override fun create(state: SavedStateHandle): SavedStateViewModel4
  }

  init {
    Log.d("construct4", (state.get("test") as String?).toString())
  }

  fun write() {
    state.set("test", "test4")
  }
}
