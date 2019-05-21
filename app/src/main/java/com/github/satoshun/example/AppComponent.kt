package com.github.satoshun.example

import dagger.Component

@Component
interface AppComponent {
  fun inject(activity: MainActivity)
}
