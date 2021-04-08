package com.skanderjabouzi.squaretest

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class MockUITestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?,
                                context: Context?): Application {
        return super.newApplication(cl, TestApp::class.java.name, context)
    }
}