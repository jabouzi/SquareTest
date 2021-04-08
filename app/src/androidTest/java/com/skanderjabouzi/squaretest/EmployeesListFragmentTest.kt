package com.skanderjabouzi.squaretest

import android.content.Intent
import android.widget.ProgressBar
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.skanderjabouzi.squaretest.core.MainActivity
import com.skanderjabouzi.squaretest.robots.EmployeesRobot
import com.skanderjabouzi.squaretest.utils.replaceProgressBarDrawable
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.concurrent.TimeUnit


@RunWith(RobolectricTestRunner::class)
class EmployeesListFragmentTest {

    private val mockWebServer = MockWebServer()

    lateinit var activityScenario: ActivityScenario<MainActivity>
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    val intent = Intent(appContext, MainActivity::class.java)
    private val employeesRobot: EmployeesRobot by lazy {
        EmployeesRobot()
    }

    @Before
    fun setup() {
        mockWebServer.start(8080)
        IdlingRegistry.getInstance().register(
            OkHttp3IdlingResource.create(
                "okhttp",
                OkHttpClient.Builder().build()
            ))
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testSuccessfulResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(FileReader.readStringFromFile("employees.json"))
            }
        }

        activityScenario = ActivityScenario.launch<MainActivity>(intent)
        onView(isAssignableFrom(ProgressBar::class.java)).perform(replaceProgressBarDrawable())
        onView(withId(R.id.employeesProgressBar))
                .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.employeesRecyclerView))
                .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.employeesErroMessage))
            .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun testEmptyResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(FileReader.readStringFromFile("employees_empty.json"))
            }
        }

        activityScenario = ActivityScenario.launch<MainActivity>(intent)

        onView(withId(R.id.employeesProgressBar))
                .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.employeesRecyclerView))
                .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.employeesErroMessage))
                .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun testMalfomedResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(FileReader.readStringFromFile("employees_malformed.json"))
            }
        }

        activityScenario = ActivityScenario.launch<MainActivity>(intent)

        onView(withId(R.id.employeesProgressBar))
                .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.employeesRecyclerView))
                .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.employeesErroMessage))
                .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun testFailedResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().throttleBody(1024, 5, TimeUnit.SECONDS)
            }
        }

        activityScenario = ActivityScenario.launch<MainActivity>(intent)

        onView(withId(R.id.employeesProgressBar))
                .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.employeesRecyclerView))
                .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.employeesErroMessage))
                .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun testViewsAreVisibleAndHasTexts () {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(FileReader.readStringFromFile("employees.json"))
            }
        }

        activityScenario = ActivityScenario.launch<MainActivity>(intent)
        onView(isAssignableFrom(ProgressBar::class.java)).perform(replaceProgressBarDrawable())
        employeesRobot.verifyEmployeesListTitlesAreDisplayed()
        employeesRobot.verifyEmployeeItemContactHasText(0, "5553280123 - jmason.demo@squareup.com")
        employeesRobot.scrollEmployeesListTo(5)
        employeesRobot.verifyEmployeeItemFullnameHasText(5, "Nate Anderson")
    }

}