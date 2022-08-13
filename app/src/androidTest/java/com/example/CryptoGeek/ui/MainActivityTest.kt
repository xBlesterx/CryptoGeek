package com.example.CryptoGeek.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import com.example.CryptoGeek.R
import java.util.regex.Pattern


@RunWith(AndroidJUnit4::class)
class MainActivityTest : TestCase() {
    @Test
    fun Test1() {
        val activityScenario = ActivityScenario.launch(SignInActivity::class.java)
        onView(withId(R.id.textViewIN)).perform(click())

        onView(withId(R.id.signUP)).check(matches(ViewMatchers.isDisplayed()))

        Espresso.pressBack()

    }

    @Test
    fun Test2() {
        val activityScenario = ActivityScenario.launch(SignUpActivity::class.java)
        onView(withId(R.id.textViewUP)).perform(click())

        onView(withId(R.id.signIN)).check(matches(ViewMatchers.isDisplayed()))

        Espresso.pressBack()

    }
    @Test
    fun Test3() {
        val activityScenario = ActivityScenario.launch(SignInActivity::class.java)
        onView(withId(R.id.textViewIN)).perform(click())

        onView(withId(R.id.signUP)).check(matches(ViewMatchers.isDisplayed()))

        Espresso.pressBack()

    }
    @Test
    fun Test4() {
        val activityScenario = ActivityScenario.launch(SignUpActivity::class.java)
        onView(withId(R.id.textViewUP)).perform(click())

        onView(withId(R.id.signIN)).check(matches(ViewMatchers.isDisplayed()))

        Espresso.pressBack()

    }
    @Test
    fun Test5() {
        val activityScenario = ActivityScenario.launch(SignInActivity::class.java)
        onView(withId(R.id.textViewIN)).perform(click())

        onView(withId(R.id.signUP)).check(matches(ViewMatchers.isDisplayed()))

        Espresso.pressBack()

    }
}







