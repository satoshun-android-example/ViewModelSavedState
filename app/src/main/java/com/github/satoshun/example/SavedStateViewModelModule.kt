package com.github.satoshun.example

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@AssistedModule
@Module(includes = [AssistedInject_SavedStateViewModelModule::class])
interface SavedStateViewModelModule
