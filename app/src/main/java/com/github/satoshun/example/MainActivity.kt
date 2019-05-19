package com.github.satoshun.example

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act)

    val vm = ViewModelProvider(this, SavedStateVMFactory(this))
      .get(SavedStateViewModel::class.java)
    vm.write()
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
