package com.example.lookup.services

import android.util.Log
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FlightDataServiceTests {
    private var flightService = FlightDataService()
    @Test
    fun output_isValid() {
        flightService.getFlightData { flightList ->
            flightList.forEach{ flight ->
                Log.d("TEST", flight.toString())
            }
        }

    }
}