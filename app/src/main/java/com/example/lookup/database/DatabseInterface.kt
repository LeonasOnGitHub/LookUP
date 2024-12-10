package com.example.lookup.database

import com.example.lookup.models.Aircraft

interface DatabaseInterface {

    /**
     *  Returns all collected Airplanes
     */
    fun getAllAirplanes():List<Aircraft>

    /**
     *   Adds a Aircraft to the collectionDB
     */
    fun addAircraft(aircraft: Aircraft):Boolean

    /**
     * returns
     */
    fun getMostRecentAirplanes(): List<Aircraft>
}