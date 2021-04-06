package com.skanderjabouzi.squaretest.data.net

import com.skanderjabouzi.squaretest.data.model.Employees
import retrofit2.Response
import retrofit2.http.GET

interface EmployeesApi {
    @GET("employees.json")
    suspend fun getEmployees(): Response<Employees>

    @GET("employees_malformed.json.json")
    suspend fun getMalformedEmployees():  Response<Employees>

    @GET("employees_empty.json.json")
    suspend fun getEmptyEmployees():  Response<Employees>
}