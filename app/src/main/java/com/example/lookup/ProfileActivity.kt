package com.example.lookup

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lookup.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences


        // Load saved data
        val profileData = getProfileData()
        binding.etName.setText(profileData.name)
        binding.etFavoriteModel.setText(profileData.favAircraft)

        // Save data when button is clicked
        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val favoriteModel = binding.etFavoriteModel.text.toString()
            saveProfileData(name, favoriteModel)

            Toast.makeText(this, "Profile saved!", Toast.LENGTH_SHORT).show()
        }

    }

    fun getProfileData(): ProfileData {
        sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")
        val favAircraft = sharedPreferences.getString("favoriteModel", "")

        return ProfileData(name.toString(), favAircraft.toString())
    }

    fun saveProfileData(name: String, favAircraft: String){
        sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        editor.putString("name", name)
        editor.putString("favoriteModel", favAircraft)
        editor.apply()

    }
}

data class ProfileData(
    val name: String,
    val favAircraft: String
)
