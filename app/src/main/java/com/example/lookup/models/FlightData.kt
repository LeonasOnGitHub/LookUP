package com.example.lookup.models

data class FlightData(
    val id: String,
    val callSign: String,
    val latitude: Double,
    val longitude: Double,
    val origin: String,
    val category: Int,
    val velocity: Double
)