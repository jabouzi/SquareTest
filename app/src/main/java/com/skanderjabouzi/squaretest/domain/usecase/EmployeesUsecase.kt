package com.skanderjabouzi.squaretest.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.skanderjabouzi.squaretest.data.model.Employee
import com.skanderjabouzi.squaretest.data.model.Employees
import com.skanderjabouzi.squaretest.domain.gateway.EmployeesRepository
import com.skanderjabouzi.squaretest.utils.ResultState
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class EmployeesUsecase @Inject constructor(
    val employeesRepository: EmployeesRepository) {

    val employessList = MutableLiveData<List<Employee>>()
    val error = MutableLiveData<String>()

    suspend fun getEmployeesList() {
        getRequestFromApi(employeesRepository.getEmployeesList())?.let {
            when(it) {
                is ResultState.Success -> {
                    val employees = (it.data as Employees).employees
                    employees?.let {
                        employessList.postValue(it)
                    }
                }
                else -> error.postValue((it as ResultState.Error).error)
            }
        }
    }

    private fun getRequestFromApi(response: Response<*>): ResultState? {
        return try {
            if (response.isSuccessful) {
                response.body()?.let { ResultState.Success(it) }
            } else {
                ResultState.Error(response.message())
            }
        } catch (error: IOException) {
            error.message?.let { ResultState.Error(it) }
        }
    }

}