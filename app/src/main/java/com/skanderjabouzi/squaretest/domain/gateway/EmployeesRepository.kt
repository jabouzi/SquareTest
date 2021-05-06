package com.skanderjabouzi.squaretest.domain.gateway

import com.skanderjabouzi.squaretest.core.App
import com.skanderjabouzi.squaretest.data.model.Employees
import com.skanderjabouzi.squaretest.data.net.RetrofitClient
import retrofit2.Response
import javax.inject.Inject

class EmployeesRepository @Inject constructor() {

    @set:Inject private var retrofitClient: RetrofitClient = App.INSTANCE.appComponent.getRetrofitClient()

    suspend fun getEmployeesList(): Response<Employees> = retrofitClient.getEmployees()
}