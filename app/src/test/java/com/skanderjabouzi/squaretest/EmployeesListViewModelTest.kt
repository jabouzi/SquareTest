package com.skanderjabouzi.squaretest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fasterxml.jackson.databind.DeserializationConfig
import com.fasterxml.jackson.databind.DeserializationFeature
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import com.skanderjabouzi.squaretest.data.model.Employee
import com.skanderjabouzi.squaretest.domain.usecase.EmployeesUsecase
import com.skanderjabouzi.squaretest.presentation.EmployeesListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.core.Is
import org.junit.*
import org.mockito.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.skanderjabouzi.squaretest.data.model.Employees
import com.skanderjabouzi.squaretest.utils.ResultState
import retrofit2.Response

class EmployeesListViewModelTest: BaseTest() {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var usecase: EmployeesUsecase

    lateinit var viewModel: EmployeesListViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.initMocks(this)
        viewModel = EmployeesListViewModel(usecase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `run getEmployeesList not null and validate response`() {
        runBlocking {
            doReturn(fullEmployees).whenever(usecase).getEmployeesList()
            viewModel.getEmployeesList()
            Assert.assertThat(
                LiveDataTestUtil.getValue(viewModel.employees), Is.`is`(((fullEmployees as ResultState.Success).data as Employees).employees)
            )
            Mockito.verify(usecase).getEmployeesList()
        }
    }

    @Test
    fun `run getEmptyEmployeesList not null and validate response`() {
        runBlocking {
            doReturn(emptyEmployees).whenever(usecase).getEmployeesList()
            viewModel.getEmployeesList()
            Assert.assertThat(
                LiveDataTestUtil.getValue(viewModel.employees), Is.`is`(((emptyEmployees as ResultState.Success).data as Employees).employees)
            )
            Mockito.verify(usecase).getEmployeesList()
        }
    }

    @Test
    fun `run getMalformedEmployeesList not null and validate response`() {
        runBlocking {
            doReturn(malformedEmployees).whenever(usecase).getEmployeesList()
            viewModel.getEmployeesList()
            Assert.assertThat(
                LiveDataTestUtil.getValue(viewModel.error), Is.`is`((malformedEmployees as ResultState.Error).error)
            )
            Mockito.verify(usecase).getEmployeesList()
        }
    }

    private val fullEmployees: ResultState?
        get() {
            val mapper = jacksonObjectMapper()
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
            return readJsonFile("employees.json")?.let { mapper.readValue<Employees>(it) }?.let {
                ResultState.Success(
                    it
                )
            }
        }

    private val emptyEmployees: ResultState?
        get() {
            val mapper = jacksonObjectMapper()
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
            return readJsonFile("employees_empty.json")?.let { mapper.readValue<Employees>(it) }?.let {
                ResultState.Success(
                    it
                )
            }
        }

    private val malformedEmployees: ResultState?
        get() {
            try {
                val mapper = jacksonObjectMapper()
                mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                return readJsonFile("employees_malformed.json")?.let { mapper.readValue<Employees>(it) }?.let {
                    ResultState.Success(
                        it
                    )
                }

            } catch (e: Exception) {
                return ResultState.Error(e.localizedMessage)
            }
        }
}