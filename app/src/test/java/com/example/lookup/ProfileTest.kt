package com.example.lookup

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import com.google.common.truth.Truth.assertThat
import org.robolectric.Robolectric

@RunWith(RobolectricTestRunner::class)
class ProfileTest {

    @Test
    fun testSaveAndRetrieveProfileData() {
        // Simuliere eine Android-Umgebung
        val profile = Robolectric.buildActivity(ProfileActivity::class.java).create().get()


        // Speichere Daten
        val name = "John Doe"
        val favoriteModel = "Boeing 747"
        profile.saveProfileData(name, favoriteModel)

        // Rufe die gespeicherten Daten ab
        val profileData = profile.getProfileData()

        // Prüfe die Daten
        assertThat(profileData.name).isEqualTo(name)
        assertThat(profileData.favAircraft).isEqualTo(favoriteModel)
    }
}
