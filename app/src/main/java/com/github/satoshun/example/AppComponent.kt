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
    modules = [SavedStateViewModel3Module::class]
  )
  fun contributeMainActivity(): MainActivity
}
