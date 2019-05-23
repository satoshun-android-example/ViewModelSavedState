package com.github.satoshun.example

import dagger.Component

@Component(modules = [SavedStateViewModel3Module::class])
interface AppComponent {
  fun inject(activity: MainActivity)
}
