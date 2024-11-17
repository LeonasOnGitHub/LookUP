package com.example.lookup.services

import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import com.example.lookup.models.FlightData

class FlightDataService {

    private val client = OkHttpClient()
    private val apiUrl = "https://opensky-network.org/api/states/all"

    fun getFlightData(callback: (List<FlightData>) -> Unit) {
        val request = Request.Builder().url(apiUrl).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback(emptyList())
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let {
                    val flightList = parseFlightData(it)
                    callback(flightList)
                }
            }
        })
    }

    private fun parseFlightData(jsonData: String): List<FlightData> {
        val flightList = mutableListOf<FlightData>()
        val jsonObject = JSONObject(jsonData)
        val states = jsonObject.getJSONArray("states")

        for (i in 0 until states.length()) {
            val flight = states.getJSONArray(i)
            val flightData = FlightData(
                id = flight.getString(0),
                latitude = flight.getDouble(5),
                longitude = flight.getDouble(6),
                altitude = flight.getDouble(7)
            )
            flightList.add(flightData)
        }
        return flightList
    }
}


