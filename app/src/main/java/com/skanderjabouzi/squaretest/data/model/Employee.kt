package com.skanderjabouzi.squaretest.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Employee(
    @JsonProperty("uuid")
    var uuid: String,
    @JsonProperty("full_name")
    var full_name: String,
    @JsonProperty("phone_number")
    var phone_number: String,
    @JsonProperty("email_address")
    var email_address: String,
    @JsonProperty("biography")
    var biography: String,
    @JsonProperty("photo_url_small")
    var photo_url_small: String,
    @JsonProperty("photo_url_large")
    var photo_url_large: String,
    @JsonProperty("team")
    var team: String,
    @JsonProperty("employee_type")
    var employee_type: String
)