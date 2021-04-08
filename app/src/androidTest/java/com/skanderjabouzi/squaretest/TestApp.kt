package com.skanderjabouzi.squaretest

import com.skanderjabouzi.squaretest.core.App

class TestApp : App() {

    var url = "http://127.0.0.1:8080"

    override fun getBaseUrl(): String {
        return url
    }
}