package com.skanderjabouzi.squaretest.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Employee(
    @JsonProperty("uuid")
    var uuid: String,
    @JsonProperty("full_name")
    var fullName: String,
    @JsonProperty("phone_number")
    var phoneNumber: String,
    @JsonProperty("email_address")
    var emailAddress: String,
    @JsonProperty("biography")
    var biography: String,
    @JsonProperty("photo_url_small")
    var photoUrlSmall: String,
    @JsonProperty("photo_url_large")
    var photoUrlLarge: String,
    @JsonProperty("team")
    var team: String,
    @JsonProperty("employee_type")
    var employeeType: String
)