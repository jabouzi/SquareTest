package com.skanderjabouzi.squaretest.core

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.skanderjabouzi.squaretest.R

class MainActivity : AppCompatActivity() {

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationController = findNavController(R.id.navigationHostFragment)
    }

    override fun onBackPressed() {}

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}