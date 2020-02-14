package com.github.satoshun.example

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Component(
  modules = [
    MainActivityModule::class,
    AndroidInjectionModule::class
  ]
)
interface AppComponent : AndroidInjector<App>

@Module
interface MainActivityModule {
  @ContributesAndroidInjector(
    modules = [
      MainActivityViewModel::class,
      SavedStateViewModelModule::class
    ]
  )
  fun contributeMainActivity(): MainActivity
}

@Module
interface MainActivityViewModel {
  @Binds
  fun bindFragmentActivity(activity: MainActivity): FragmentActivity

  @ViewModelKey(SavedStateViewModel4::class)
  @IntoMap @Binds
  fun bind(
    factory: SavedStateViewModel4.Factory
  ): SavedStateVMFactory

  companion object {
    @Provides
    fun provideSavedStateViewModel5(
      activity: MainActivity,
      factory: SavedStateViewModel5.Factory
    ): SavedStateViewModel5 =
      ViewModelProvider(activity, factory).get()
  }
}
