package com.example.lookup.services

import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import com.example.lookup.models.FlightData
import org.json.JSONArray

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

    override fun getFlightDataById(id: String):String {
        val request = Request.Builder().url(apiUrl).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException):  {
                e.printStackTrace()
                callback()
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
                    flightList.add(getFlightDataObj(flight))
                }
        }

        return flightList
    }

    private fun getFlightDataObj(flight: JSONArray): FlightData {
        val flightData = FlightData(
            id = flight.optString(0, null),
            callSign = flight.optString(1, null),
            longitude = flight.optDouble(5),
            latitude = flight.optDouble(6),
            origin = flight.getString(2),
            category = flight.getInt(16)
        )
        return flightData
    }
}


