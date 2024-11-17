package com.example.lookup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lookup.database.DatabaseHelper
import com.example.lookup.databinding.ActivityCollectionBinding
import com.example.lookup.models.Aircraft

class CollectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCollectionBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize database helper
        databaseHelper = DatabaseHelper(this)

        // Get the list of collected aircrafts
        val aircraftList = databaseHelper.getAllAircrafts()

        // Setup RecyclerView
        binding.rvCollection.layoutManager = LinearLayoutManager(this)
        binding.rvCollection.adapter = AircraftAdapter(aircraftList)
    }
}
