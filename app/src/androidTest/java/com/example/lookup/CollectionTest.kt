package com.example.lookup

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.lookup.models.FlightData
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CompletableFuture.allOf

@RunWith(AndroidJUnit4::class)
class CollectionTest {

    private val testAircraftList = listOf(
        FlightData("1", "Alpha", 40.0, -70.0, "USA", 1, 250.0),
        FlightData("2", "Bravo", 41.0, -71.0, "Canada", 2, 300.0),
        FlightData("3", "Charlie", 42.0, -72.0, "Mexico", 3, 400.0),
    )

    @Test
    fun testRecyclerViewItemCount() {
        // Start die Activity mit einem RecyclerView
        val scenario = ActivityScenario.launch(CollectionActivity::class.java)

        scenario.onActivity { activity ->
            // Setzen des Adapters mit Testdaten
            val recyclerView = activity.findViewById<RecyclerView>(R.id.rvCollection)
            recyclerView.adapter = AircraftAdapter(testAircraftList)
        }

        // Überprüfen, ob die RecyclerView die richtige Anzahl an Items anzeigt
        onView(withId(R.id.rvCollection)).check { view, _ ->
            val recyclerView = view as RecyclerView
            assert(recyclerView.adapter?.itemCount == testAircraftList.size)
        }
    }

    @Test
    fun testRecyclerViewItemCountWithZero() {
        // Start die Activity mit einem RecyclerView
        val scenario = ActivityScenario.launch(CollectionActivity::class.java)

        scenario.onActivity { activity ->
            // Setzen des Adapters mit Testdaten
            val recyclerView = activity.findViewById<RecyclerView>(R.id.rvCollection)
            recyclerView.adapter = AircraftAdapter(emptyList())
        }

        // Überprüfen, ob die RecyclerView die richtige Anzahl an Items anzeigt
        onView(withId(R.id.rvCollection)).check { view, _ ->
            val recyclerView = view as RecyclerView
            assert(recyclerView.adapter?.itemCount == 0)
        }
    }

    @Test
    fun testRecyclerViewItemContent() {
        // Start die Activity mit einem RecyclerView
        val scenario = ActivityScenario.launch(CollectionActivity::class.java)

        scenario.onActivity { activity ->
            // Setzen des Adapters mit Testdaten
            val recyclerView = activity.findViewById<RecyclerView>(R.id.rvCollection)
            recyclerView.adapter = AircraftAdapter(testAircraftList)
        }

        // Scrolle zu einem bestimmten Item und überprüfe dessen Inhalte
        onView(withId(R.id.rvCollection))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))

        onView(withId(R.id.rvCollection))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText("Callsign: Bravo"))
                )
            )
            .check(matches(isDisplayed()))

        onView(withId(R.id.rvCollection))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText("Callsign: Alpha"))
                )
            )
            .check(matches(isDisplayed()))

        onView(withId(R.id.rvCollection))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText("Callsign: Charlie"))
                )
            )
            .check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerViewItemContentEmpty() {
        // Start die Activity mit einem RecyclerView
        val scenario = ActivityScenario.launch(CollectionActivity::class.java)

        scenario.onActivity { activity ->
            // Setzen des Adapters mit Testdaten
            val recyclerView = activity.findViewById<RecyclerView>(R.id.rvCollection)
            recyclerView.adapter = AircraftAdapter(emptyList())
        }

    }
}