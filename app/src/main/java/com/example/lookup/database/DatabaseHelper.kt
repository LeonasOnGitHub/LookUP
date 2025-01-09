package com.example.lookup.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
/*
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), DatabaseInterface {

    companion object {
        private const val DATABASE_NAME = "flightTracker.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "aircraft"
        const val COLUMN_ID = "id"
        const val COLUMN_MODEL = "model"
        const val COLUMN_TIMESTAMP = "timestamp"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID TEXT PRIMARY KEY, " +
                "$COLUMN_MODEL TEXT, " +
                "$COLUMN_TIMESTAMP BLOB)")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    override fun addAircraft(aircraft: Aircraft): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_ID, aircraft.id)
        values.put(COLUMN_MODEL, aircraft.model)
        values.put(COLUMN_TIMESTAMP, aircraft.timestamp)

        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        return (result != -1L)
    }

    override fun getMostRecentAirplanes(): List<Aircraft> {
        TODO("Not yet implemented")
    }

    @SuppressLint("Range")
    override fun getAllAirplanes(): List<Aircraft> {
        val aircraftList = mutableListOf<Aircraft>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val aircraft = Aircraft(
                    id = cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                    model = cursor.getString(cursor.getColumnIndex(COLUMN_MODEL)),
                    timestamp = cursor.getLong(cursor.getColumnIndex(COLUMN_TIMESTAMP))
                )
                aircraftList.add(aircraft)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return aircraftList
    }
}
*/