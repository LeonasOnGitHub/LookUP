package com.example.lookup

import android.content.Context
import android.content.SharedPreferences
import android.widget.Button
import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.lookup.databinding.ActivityProfileBinding
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileTest {

    private lateinit var sharedPreferences: SharedPreferences
    private val testProfileData = ProfileData("John Doe", "Airbus A380")

    @Before
    fun setUp() {
        // Set up SharedPreferences für den Test
        sharedPreferences = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation()
            .targetContext
            .getSharedPreferences("UserProfile", Context.MODE_PRIVATE)

        sharedPreferences.edit()
            .putString("name", testProfileData.name)
            .putString("favoriteModel", testProfileData.favAircraft)
            .apply()
    }

    @After
    fun tearDown() {
        // Löschen von SharedPreferences nach dem Test
        sharedPreferences.edit().clear().apply()
    }

    @Test
    fun testActivityLoadsSavedData() {
        val scenario = ActivityScenario.launch(ProfileActivity::class.java)

        scenario.onActivity { activity ->
            // Zugriff auf die tatsächlichen UI-Elemente der Aktivität
            val name = activity.findViewById<EditText>(R.id.etName).text.toString()
            val favAircraft = activity.findViewById<EditText>(R.id.etFavoriteModel).text.toString()

            assertEquals(testProfileData.name, name)
            assertEquals(testProfileData.favAircraft, favAircraft)
        }

    }

    @Test
    fun testActivitySavesData() {
        val scenario = ActivityScenario.launch(ProfileActivity::class.java)

        scenario.onActivity { activity ->
            val etName = activity.findViewById<EditText>(R.id.etName)
            val etFavModel = activity.findViewById<EditText>(R.id.etFavoriteModel)
            val btnSave = activity.findViewById<Button>(R.id.btnSave)

            // Neue Daten eingeben und speichern
            etName.setText("Jane Smith")
            etFavModel.setText("Concorde")
            btnSave.performClick()

            // Überprüfen, ob SharedPreferences aktualisiert wurde
            val savedName = activity.getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
                .getString("name", "")
            val savedFavModel = activity.getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
                .getString("favoriteModel", "")

            assertEquals( "Jane Smith", savedName)
            assertEquals( "Concorde", savedFavModel)
        }
    }
}
