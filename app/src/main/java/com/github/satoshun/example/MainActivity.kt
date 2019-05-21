package com.github.satoshun.example

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AbstractSavedStateVMFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
  private val viewModel by viewModels<SavedStateViewModel> {
    SavedStateVMFactory(this)
  }

  @Inject lateinit var factory: SavedStateViewModel2.Factory
  private val viewModel2 by viewModels<SavedStateViewModel2> {
    factory.create(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    DaggerAppComponent.create().inject(this)
    setContentView(R.layout.main_act)

    viewModel.write()
    viewModel2.write()
  }
}

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

class SavedStateViewModel2(
  private val dummy: Dummy,
  private val state: SavedStateHandle
) : ViewModel() {
  class Factory @Inject constructor(private val dummy: Dummy) {
    fun create(owner: FragmentActivity): AbstractSavedStateVMFactory {
      return object : AbstractSavedStateVMFactory(owner, owner.intent.extras) {
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

class Dummy @Inject constructor()
