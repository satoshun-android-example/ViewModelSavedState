package com.github.satoshun.example

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateVMFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
  private val viewModel by viewModels<SavedStateViewModel> {
    SavedStateVMFactory(this)
  }

  @Inject lateinit var factory2: SavedStateViewModel2.Factory
  private val viewModel2 by viewModels<SavedStateViewModel2> {
    factory2.create(this)
  }

  @Inject lateinit var factory3: SavedStateViewModel3.Factory
  private val viewModel3 by viewModels<SavedStateViewModel3> {
    viewModelWrapper(this) { factory3.create(it) }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    DaggerAppComponent.create().inject(this)
    setContentView(R.layout.main_act)

    viewModel.write()
    viewModel2.write()
    viewModel3.write()
  }
}
