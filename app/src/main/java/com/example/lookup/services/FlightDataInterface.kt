package com.example.lookup.services

import com.example.lookup.models.FlightData

interface FlightDataInterface {

    /**
     * #return a List of all flights in your range
     */
    fun getFlightData(callback: (List<FlightData>) -> Unit)
}