package com.hfad.sturbuzz

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StarbuzzDatabaseHelper(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VERSION) {

    companion object{
        private val DATABASE_NAME = "starbuzz.db"
        private val DATABASE_VERSION = 2
    }

    override fun onCreate(db: SQLiteDatabase?) {
        updateMyDatabase(db, 0, DATABASE_VERSION)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        updateMyDatabase(db, oldVersion, newVersion)
    }

    private fun insertDrink(db: SQLiteDatabase, name: String, description: String, resourceId: Int) {
        val drinkValues = ContentValues()
        drinkValues.put("NAME", name)
        drinkValues.put("DESCRIPTION", description)
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId)
        db.insert("DRINK", null, drinkValues)
    }

    private fun updateMyDatabase(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 1 && db != null) {
            db.execSQL("CREATE TABLE DRINK("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER) "
            )
            insertDrink(db, "Latte", "Espresso and steamed milk", R.drawable.latte)
            insertDrink(db, "Cappuccino", "Espresso,hot milk, and a steamed milk foam", R.drawable.cappuccino)
            insertDrink(db, "Filter", "Our best drip coffee", R.drawable.filter)
        }

        if (oldVersion < 2 && db != null) db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC")
    }

}