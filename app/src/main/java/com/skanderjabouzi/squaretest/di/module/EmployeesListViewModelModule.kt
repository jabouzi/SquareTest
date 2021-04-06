package com.skanderjabouzi.squaretest.di.module

import androidx.lifecycle.ViewModel
import com.skanderjabouzi.squaretest.di.score.ViewModelKey
import com.skanderjabouzi.squaretest.presentation.EmployeesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class EmployeesListViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(EmployeesListViewModel::class)
    abstract fun bindsViewModel(viewModel: EmployeesListViewModel): ViewModel
}