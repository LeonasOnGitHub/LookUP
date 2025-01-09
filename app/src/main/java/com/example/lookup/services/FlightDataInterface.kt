package com.example.lookup.services

import com.example.lookup.models.FlightData

interface FlightDataInterface {

    /**
     * #return a List of all flights in your range
     */
    fun getFlightData(callback: (List<FlightData>) -> Unit)

    /**
     * returns an data object with information about the flight with the given id
     */
    fun getFlightDataById(id: String, callback: (List<FlightData>) -> Unit)
}