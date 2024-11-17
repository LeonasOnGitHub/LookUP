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
        sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE)

        // Load saved data
        binding.etName.setText(sharedPreferences.getString("name", ""))
        binding.etFavoriteModel.setText(sharedPreferences.getString("favoriteModel", ""))

        // Save data when button is clicked
        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val favoriteModel = binding.etFavoriteModel.text.toString()

            val editor = sharedPreferences.edit()
            editor.putString("name", name)
            editor.putString("favoriteModel", favoriteModel)
            editor.apply()

            Toast.makeText(this, "Profile saved!", Toast.LENGTH_SHORT).show()
        }
    }
}
