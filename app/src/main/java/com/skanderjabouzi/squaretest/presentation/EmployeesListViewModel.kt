package com.skanderjabouzi.squaretest.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skanderjabouzi.squaretest.data.model.Employee
import com.skanderjabouzi.squaretest.data.model.Employees
import com.skanderjabouzi.squaretest.domain.usecase.EmployeesUsecase
import com.skanderjabouzi.squaretest.utils.ResultState
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmployeesListViewModel @Inject constructor(val usecase: EmployeesUsecase) : ViewModel() {
    val employees = MutableLiveData<List<Employee>>()
    val error = MutableLiveData<String>()

    init {
        getEmployeesList()
    }

    fun getEmployeesList() {
        viewModelScope.launch {
            val result = usecase.getEmployeesList()

            when(result) {
                is ResultState.Success -> {
                    employees.postValue((result.data as Employees).employees)
                }
                else -> error.postValue((result as ResultState.Error).error)
            }
        }
    }
}