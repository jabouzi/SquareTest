package com.skanderjabouzi.squaretest

import android.app.Application
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.test.core.app.ApplicationProvider
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.skanderjabouzi.squaretest.data.model.Employee
import com.skanderjabouzi.squaretest.data.model.Employees
import com.skanderjabouzi.squaretest.presentation.EmployeesListAdapter
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import com.google.common.truth.Truth.assertThat
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1], manifest=Config.NONE)
@RunWith(RobolectricTestRunner::class)
class EmployeesListAdapterTest: BaseTest() {
    private lateinit var context: Application

    lateinit var adapter: EmployeesListAdapter

    @Before
    fun setUp() {
        adapter = EmployeesListAdapter()
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun `test Adapter`() {
        fullEmployees?.let { adapter.setEmployees(it) }
        adapter.notifyDataSetChanged()
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.employees_list_item, null, false)
        adapter.onCreateViewHolder(FrameLayout(context), 0)
        val holder = adapter.EmployeesViewHolder(adapter.itemBinding)
        adapter.onBindViewHolder(holder, 0)
        Assert.assertEquals(11, adapter.itemCount)
        assertThat(holder.itemBinding.fullName.text.toString()).isEqualTo("Justine Mason")
    }

    private val fullEmployees: List<Employee>?
        get() {
            val mapper = jacksonObjectMapper()
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
            return readJsonFile("employees.json")?.let { mapper.readValue<Employees>(it) }?.let {
                    it.employees
            }
        }

}