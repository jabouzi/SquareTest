package com.skanderjabouzi.squaretest.presentation

import android.util.Log
import androidx.lifecycle.LiveData
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
    private val _employees = MutableLiveData<List<Employee>>()
    private val _error = MutableLiveData<String>()

    val employees: LiveData<List<Employee>>
            get() = _employees

    val error: LiveData<String>
        get() = _error

    init {
        getEmployeesList()
    }

    fun reload() {
        getEmployeesList()
    }

    private fun getEmployeesList() {
        viewModelScope.launch {
            val result = usecase.getEmployeesList()

            when(result) {
                is ResultState.Success -> {
                    _employees.value = (result.data as Employees).employees
                    _error.value = ""
                }
                else -> {
                    _employees.value = ArrayList()
                    _error.value = (result as ResultState.Error).error
                }
            }
        }
    }
}