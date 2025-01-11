package com.example.lookup

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.platform.util.TestOutputEmitter.takeScreenshot

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

//    @Test
//    fun testMainActivityUI() {
//        // Warte, bis die MainActivity vollständig geladen ist
//        onView(withId(R.id.btnMap)).check(matches(isDisplayed()))
//
//        // Mach einen Screenshot der MainActivity
//        takeScreenshot("main_activity")
//    }

    @Test
    fun testButtonClicksStartCorrectActivityProfile() {
        ActivityScenario.launch(MainActivity::class.java)

        // Test für Button Profile
        onView(withId(R.id.btnProfile)).perform(click())
        // Überprüfen, ob ProfileActivity gestartet wird
        onView(withId(R.id.btnSave)).check(matches(isDisplayed()))
    }

    @Test
    fun testButtonClicksStartCorrectActivityCollection() {
        ActivityScenario.launch(MainActivity::class.java)
        // Test für Button Collection
        onView(withId(R.id.btnCollection)).perform(click())
        // Überprüfen, ob CollectionActivity gestartet wird
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))

    }

//    @Test
//    fun testButtonClicksStartCorrectActivityMap() {
//        // Starte die MainActivity
//        ActivityScenario.launch(MainActivity::class.java)
//
//        // Test für Button Map
//        onView(withId(R.id.btnMap)).perform(click())
//        // Überprüfen, ob MapActivity gestartet wird
//        onView(withId(R.id.mapView)).check(matches(isDisplayed()))
//    }





}
