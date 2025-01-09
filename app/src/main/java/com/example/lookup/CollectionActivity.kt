package com.example.lookup

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lookup.databinding.ActivityCollectionBinding
import com.example.lookup.models.FlightData
import org.json.JSONArray

class CollectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCollectionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize database helper


        // Get the list of collected airplanes
        val aircraftList = getFlightDataList(this)

        // Setup RecyclerView
        binding.rvCollection.layoutManager = LinearLayoutManager(this)
        binding.rvCollection.adapter = AircraftAdapter(aircraftList)
    }

    private fun getFlightDataList(context: Context): List<FlightData> {
        val sharedPreferences = context.getSharedPreferences("FlightDataPrefs", Context.MODE_PRIVATE)
        val jsonString = sharedPreferences.getString("savedFlightList", "[]")
        val jsonArray = JSONArray(jsonString)

        val flightList = mutableListOf<FlightData>()
        for (i in 0 until jsonArray.length()){
            val jsonObject = jsonArray.getJSONObject(i)
            flightList.add(FlightData.fromJSON(jsonObject.toString()))
        }
        return flightList
    }
}
