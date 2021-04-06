package com.skanderjabouzi.squaretest.data.net

import com.skanderjabouzi.squaretest.data.model.Employees
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitClient @Inject constructor(val retrofit: Retrofit) {

    private val employeesApi: EmployeesApi

    init {
        employeesApi = retrofit.create(EmployeesApi::class.java)
    }

    suspend fun getEmployees(): Response<Employees> {
        return employeesApi.getEmployees()
    }

    suspend fun getMalformedEmployees(): Response<Employees> {
        return employeesApi.getMalformedEmployees()
    }

    suspend fun getEmptyEmployees(): Response<Employees> {
        return employeesApi.getEmptyEmployees()
    }

}