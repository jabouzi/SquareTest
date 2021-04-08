package com.skanderjabouzi.squaretest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.skanderjabouzi.squaretest.domain.gateway.EmployeesRepository
import com.skanderjabouzi.squaretest.domain.usecase.EmployeesUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.Response
import org.junit.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class EmployeesUsecaseTest: BaseTest()  {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: EmployeesRepository

    lateinit var usecase: EmployeesUsecase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        usecase = EmployeesUsecase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `run getEmployeesList not null and validate called methods`() {
        runBlocking {
            doReturn(Response.Builder()).whenever(repository).getEmployeesList()
            usecase.getEmployeesList()
            verify(repository).getEmployeesList()
        }
    }
}