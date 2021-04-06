package com.skanderjabouzi.squaretest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skanderjabouzi.squaretest.domain.usecase.EmployeesUsecase
import kotlinx.coroutines.launch
import okhttp3.internal.userAgent
import javax.inject.Inject

class EmployeesListViewModel @Inject constructor(val usecase: EmployeesUsecase) : ViewModel() {
    val employees = usecase.employessList
    val error = usecase.error

    fun getEmployeesList() {
        viewModelScope.launch {
            usecase.getEmployeesList()
        }
    }
}