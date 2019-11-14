package com.hfad.sturbuzz

import android.app.Activity
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_drink.*

class DrinkActivity :Activity(){

    var EXTRA_DRINKID ="drinkId"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink)
        val drinkId =intent.getLongExtra("EXTRA_DRINKID",213)
//        val drink= Drink.drinks[drinkId.toInt()]
//        photo.setImageResource(drink.imageResourceId)
//        name.text=drink.name
//        description.text=drink.description
//
        val starbuzzDatabaseHelper:SQLiteOpenHelper=StarbuzzDatabaseHelper(this,null,null,2)
        try {
            val db=starbuzzDatabaseHelper.readableDatabase
            val cursor=db.query("DRINK", arrayOf("NAME","DESCRIPTION","IMAGE_RESOURCE_ID"),
                "_id=?", arrayOf(drinkId.toString()),null,null, null)
            if (cursor.moveToFirst()){
                name.text=cursor.getString(0)
                description.text=cursor.getString(1)
                photo.setImageResource(cursor.getInt(2))
                photo.contentDescription=cursor.getString(2)
                Toast.makeText(this,drinkId.toString(),Toast.LENGTH_SHORT).show() // !!!!!
            }
            cursor.close()
            db.close()
        } catch (e:SQLiteException){
            Toast.makeText(this,"Database unavialable",Toast.LENGTH_SHORT).show()
        }
    }

}
