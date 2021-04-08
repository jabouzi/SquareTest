package com.skanderjabouzi.squaretest.utils

sealed class ResultState {
    data class Success(val data: Any) : ResultState()
    data class Error(val error: String) : ResultState()
    class Unknown() : ResultState()
}