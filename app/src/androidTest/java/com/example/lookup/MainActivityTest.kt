package com.example.lookup

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun testButtonClicksStartCorrectActivity() {
        // Starte die MainActivity
        ActivityScenario.launch(MainActivity::class.java)


        // Test für Button Map
        onView(withId(R.id.btnMap)).perform(click())
        // Überprüfen, ob MapActivity gestartet wird
        onView(withId(R.id.mapView)).check(matches(isDisplayed()))

        // Test für Button Collection
        onView(withId(R.id.btnCollection)).perform(click())
        // Überprüfen, ob CollectionActivity gestartet wird
        onView(withId(R.id.rvCollection)).check(matches(isDisplayed()))

        // Test für Button Profile
        onView(withId(R.id.btnProfile)).perform(click())
        // Überprüfen, ob ProfileActivity gestartet wird
        onView(withId(R.id.btnSave)).check(matches(isDisplayed()))
    }
}
