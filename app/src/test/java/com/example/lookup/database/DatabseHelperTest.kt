package com.example.lookup.database

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.sql.Timestamp

class DatabaseHelperTest {
    private lateinit var db: DatabaseInterface

    @Test
    fun insertSelectTest() {
        val time = System.currentTimeMillis()
        val aircraft = Aircraft(
            id = "1",
            model = "test",
            timestamp = time

        )
        db.addAircraft(aircraft)

        val airplanes = db.getAllAirplanes()

        assertEquals(airplanes.size, 1)
        assertEquals(airplanes[0].id, "1")
        assertEquals(airplanes[0].model, "test")
        assertEquals(airplanes[0].timestamp, time)
    }
}