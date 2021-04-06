package com.skanderjabouzi.nbateamviewer.di.modules

import androidx.lifecycle.ViewModelProvider
import com.skanderjabouzi.squaretest.utils.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}