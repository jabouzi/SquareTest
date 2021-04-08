package com.skanderjabouzi.squaretest.utils

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ProgressBar
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.actionWithAssertions
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import org.hamcrest.Matcher


fun replaceProgressBarDrawable(): ViewAction? {
    return actionWithAssertions(object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return isAssignableFrom(ProgressBar::class.java)
        }

        override fun getDescription(): String {
            return "replace the ProgressBar drawable"
        }

        override fun perform(uiController: UiController, view: View) {
            // Replace the indeterminate drawable with a static red ColorDrawable
            val progressBar = view as ProgressBar
            progressBar.indeterminateDrawable = ColorDrawable(-0x10000)
            uiController.loopMainThreadUntilIdle()
        }
    })
}