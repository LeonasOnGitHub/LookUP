package com.example.lookup.services

import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import com.example.lookup.models.FlightData

class FlightDataService: FlightDataInterface {

    private val client = OkHttpClient()
    private val apiUrl = "https://opensky-network.org/api/states/all"
    //private val apiUrl = "https://opensky-network.org/api/states/all?lamin=45.8389&lomin=5.9962&lamax=47.8229&lomax=10.5226"

    override fun getFlightData(callback: (List<FlightData>) -> Unit) {
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

    //parse flight data in FlightData class and adds it to the list
    private fun parseFlightData(jsonData: String): List<FlightData> {
        val flightList = mutableListOf<FlightData>()
        val jsonObject = JSONObject(jsonData)
        val states = jsonObject.getJSONArray("states")

        for (i in 0 until states.length()) {
            val flight = states.getJSONArray(i)
            if (!flight.getBoolean(8)) {
                    val flightData = FlightData(
                        id = flight.optString(0, null),
                        callSign = flight.optString(1, null),
                        longitude = flight.optDouble(5),
                        latitude = flight.optDouble(6)
                    )
                    flightList.add(flightData)
                }
        }

        return flightList
    }
}


