package com.example.CryptoGeek

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.CryptoGeek.ui.MainActivity
import org.junit.Rule
import org.junit.Test


class MainActivityTest{
    @get:Rule
    val activityRule : ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isListFragmentVisible_onAppLaunch(){
        onView(withId(R.id.coin_recycler_view)).check(matches(isDisplayed()))
    }
}