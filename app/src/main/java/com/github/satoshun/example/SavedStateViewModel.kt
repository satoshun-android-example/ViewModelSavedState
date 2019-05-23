package com.github.satoshun.example

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SavedStateViewModel(
  private val state: SavedStateHandle
) : ViewModel() {
  init {
    Log.d("construct", (state.get("test") as String?).toString())
  }

  fun write() {
    state.set("test", "test")
  }
}
