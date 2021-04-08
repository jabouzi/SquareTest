package com.skanderjabouzi.squaretest.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Employees(
    @JsonProperty("employees")
    var employees: List<Employee>
)