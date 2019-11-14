package com.hfad.sturbuzz

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import kotlinx.android.synthetic.main.activity_drink_category.*
import android.database.sqlite.SQLiteException
import android.widget.Toast


class DrinkCategoryActivity : Activity() {

    lateinit var db: SQLiteDatabase
    lateinit var cursor: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_category)

//////////////////////////////////////////////////////////////////////////////////////////
//        val names:  MutableList<String> = mutableListOf()                             //
//        for (i in Drink.drinks.indices) names.add(Drink.drinks[i].name)               //
//        list_drinks.adapter = ArrayAdapter<String>(                                   //
//            this,                                                                     //
//            android.R.layout.simple_list_item_1,                                      //
//            names)                                                                    //
//////////////////////////////////////////////////////////////////////////////////////////

        try {
            db = StarbuzzDatabaseHelper(this, null, null, 2)
                .readableDatabase
            cursor = db.query(
                "DRINK", arrayOf("_id", "NAME"),
                null, null, null, null, null
            )
            list_drinks.adapter = SimpleCursorAdapter(
                this, android.R.layout.simple_list_item_1,
                cursor, arrayOf("NAME"), intArrayOf(android.R.id.text1), 0
            )
        } catch (e: SQLiteException) {
            Toast.makeText(this, "Database unavialable", Toast.LENGTH_SHORT).show()
        }

        list_drinks.setOnItemClickListener { parent, view, position, id ->
            val i = Intent(this, DrinkActivity::class.java)
            i.putExtra(/*DrinkActivity.EXTRA_DRINKID*/"EXTRA_DRINKID", id)
            startActivity(i)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cursor.close()
        db.close()
    }

}
