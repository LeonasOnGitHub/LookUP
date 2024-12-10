package com.example.lookup.database

import com.example.lookup.models.Aircraft
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

        
    }
}