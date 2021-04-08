package com.skanderjabouzi.squaretest.di.component

import com.skanderjabouzi.squaretest.di.modules.ViewModelFactoryModule
import com.skanderjabouzi.squaretest.di.module.EmployeesListViewModelModule
import com.skanderjabouzi.squaretest.di.score.FragmentScope
import com.skanderjabouzi.squaretest.presentation.EmployeesListFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [EmployeesListViewModelModule::class,
    ViewModelFactoryModule::class])
interface EmployeesLIstFragmentComponent {
    fun inject(fragment: EmployeesListFragment)
}
