package com.example.lookup.models

import org.json.JSONObject
import java.io.Serializable

data class FlightData(
    val id: String,
    val callSign: String,
    val latitude: Double,
    val longitude: Double,
    val origin: String,
    val category: Int,
    val velocity: Double
) : Serializable {
    fun toJSON(): String {
        val json = JSONObject()
        json.put("callsign", callSign)
        json.put("id", id)
        json.put("origen", origin)
        json.put("category", category)
        json.put("velocity", velocity)
        json.put("long", longitude)
        json.put("lat", latitude)
        return json.toString()
    }

    companion object {
        fun fromJSON(jsonString: String): FlightData {
            val json = JSONObject(jsonString)
            return FlightData(
                id = json.get("id").toString(),
                callSign = json.get("callsign").toString(),
                origin = json.get("origen").toString(),
                category = json.get("category") as Int,
                velocity = json.get("velocity") as Double,
                latitude = json.get("lat") as Double,
                longitude = json.get("long") as Double
            )
        }
    }
}