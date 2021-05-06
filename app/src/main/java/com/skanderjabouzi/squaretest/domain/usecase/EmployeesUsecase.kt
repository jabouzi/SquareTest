package com.skanderjabouzi.squaretest.domain.usecase

import com.skanderjabouzi.squaretest.data.model.Employees
import com.skanderjabouzi.squaretest.domain.gateway.EmployeesRepository
import com.skanderjabouzi.squaretest.utils.ResultState
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class EmployeesUsecase @Inject constructor(
    private val employeesRepository: EmployeesRepository) {

    suspend fun getEmployeesList(): ResultState {
        try {
            getRequestFromApi(employeesRepository.getEmployeesList())?.let {
                return it
            }
        } catch (e: Exception) {
           return ResultState.Error(e.localizedMessage)
        }
        return ResultState.Unknown()
    }

    private fun getRequestFromApi(response: Response<Employees>): ResultState? {
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