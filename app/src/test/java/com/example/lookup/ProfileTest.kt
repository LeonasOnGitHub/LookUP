package com.example.lookup

import junit.framework.TestCase.assertEquals
import org.junit.Test


class ProfileTest {
    private lateinit var profile: ProfileActivity

    @Test
    fun testSaveAndRetrieveUsername() {
        val username = "test_user"
        val favAircraft = "test_craft"

        // Speichern des Benutzernamens
        profile.saveProfileData(username, favAircraft)

        // Abrufen und Prüfen
        val retrievedProfile = profile.getProfileData()
        assertEquals(ProfileData(username, favAircraft), retrievedProfile)
    }

    data class ProfileData(
        val name: String,
        val favAircraft: String
    )

}