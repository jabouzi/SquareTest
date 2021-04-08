package com.skanderjabouzi.squaretest.di.component

import android.content.Context
import com.skanderjabouzi.squaretest.data.net.RetrofitClient
import com.skanderjabouzi.squaretest.di.module.RetrofitModule
import com.skanderjabouzi.squaretest.di.score.AppScope
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [RetrofitModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun getRetrofitClient(): RetrofitClient
    fun getEmployeesLIstFragmentComponent(): EmployeesLIstFragmentComponent

}