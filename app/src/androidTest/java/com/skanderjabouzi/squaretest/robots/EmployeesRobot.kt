package com.skanderjabouzi.squaretest.robots

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.skanderjabouzi.squaretest.R
import com.skanderjabouzi.squaretest.matchers.IndexMatcher
import com.skanderjabouzi.squaretest.matchers.RecyclerViewMatcher
import com.skanderjabouzi.squaretest.utils.*

class EmployeesRobot {

    var context: Context = getInstrumentation().targetContext

    fun verifyEmployeesListTitlesAreDisplayed() {
        onView(IndexMatcher(withId(R.id.full_name), 1)).check(matches(isDisplayed()))
        onView(IndexMatcher(withId(R.id.contact), 1)).check(matches(isDisplayed()))
        onView(IndexMatcher(withId(R.id.biography), 1)).check(matches(isDisplayed()))
        onView(IndexMatcher(withId(R.id.type), 1)).check(matches(isDisplayed()))
        onView(IndexMatcher(withId(R.id.team), 1)).check(matches(isDisplayed()))
        onView(IndexMatcher(withId(R.id.image), 1)).check(matches(isDisplayed()))
    }

    fun verifyEmployeeItemContactHasText(position: Int, text: String) {
        onView(
            RecyclerViewMatcher(R.id.employeesRecyclerView).atPositionOnView(
                position,
                R.id.contact
            )
        ).checkHasText(text)
    }

    fun verifyEmployeeItemFullnameHasText(position: Int, text: String) {
        onView(
                RecyclerViewMatcher(R.id.employeesRecyclerView).atPositionOnView(
                        position,
                        R.id.full_name
                )
        ).checkHasText(text)
    }

    fun scrollEmployeesListTo(item: Int) {
        onView(withId(R.id.employeesRecyclerView))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(item))
    }

    fun checkHasText(id: Int, text: String) {
        id.checkHasText(text)
    }

    fun checkContainText(id: Int, text: String) {
        id.checkContainsText(text)
    }

}