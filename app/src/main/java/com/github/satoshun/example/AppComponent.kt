package com.github.satoshun.example

import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector

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
      SavedStateViewModel3Module::class
    ]
  )
  fun contributeMainActivity(): MainActivity
}

@Module
interface MainActivityViewModel {
//  @Binds @IntoMap
//  @ViewModelKey(SavedStateViewModel4::class)
//  fun bindSavedStateViewModel4(
//    vm: SavedStateViewModel4
//  ): SavedStateViewModelFactory
}
