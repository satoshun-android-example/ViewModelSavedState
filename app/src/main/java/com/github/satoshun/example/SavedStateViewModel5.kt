package com.github.satoshun.example

import android.util.Log
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SavedStateViewModel5(
  private val dummy: Dummy,
  private val state: SavedStateHandle
) : ViewModel() {
  class Factory @Inject constructor(
    activity: MainActivity,
    private val dummy: Dummy
  ) : AbstractSavedStateViewModelFactory(activity, activity.intent?.extras) {
    override fun <T : ViewModel?> create(
      key: String,
      modelClass: Class<T>,
      handle: SavedStateHandle
    ): T {
      return SavedStateViewModel5(dummy, handle) as T
    }
  }

  init {
    Log.d("construct5", (state.get("test") as String?).toString())
  }

  fun write() {
    state.set("test", "test5")
  }
}