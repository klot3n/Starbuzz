package com.hfad.sturbuzz

import android.app.Activity
import android.content.ContentValues
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_drink.*
import android.os.AsyncTask

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
            val cursor=db.query("DRINK", arrayOf("NAME","DESCRIPTION","IMAGE_RESOURCE_ID","FAVORITE"),
                "_id=?", arrayOf(drinkId.toString()),null,null, null)
            if (cursor.moveToFirst()){
                name.text=cursor.getString(0)
                description.text=cursor.getString(1)
                photo.setImageResource(cursor.getInt(2))
                photo.contentDescription=cursor.getString(2)
                favorite.isChecked=(cursor.getInt(3)==1)
            }
            cursor.close()
            db.close()
        } catch (e:SQLiteException){
            Toast.makeText(this,"Database unavialable",Toast.LENGTH_SHORT).show()
        }

        favorite.setOnClickListener {
            UpdateDrinkTask().execute(drinkId.toInt())
        }
    }

    inner class UpdateDrinkTask:AsyncTask<Int,Void,Boolean>(){
        val drinkValue=ContentValues()
        override fun onPreExecute() {
            super.onPreExecute()
            drinkValue.put("FAVORITE",favorite.isChecked())
        }

        override fun doInBackground(vararg p0: Int?): Boolean {
            val starbuzzDatabaseHelper=StarbuzzDatabaseHelper(this@DrinkActivity,null,null,2)
            try {
                val db=starbuzzDatabaseHelper.writableDatabase
                db.update("DRINK",drinkValue,"_id=?", arrayOf(p0.toString()))
                db.close()
                return true
            } catch (e: Exception) {
                Toast.makeText(this@DrinkActivity,"Database unavialable",Toast.LENGTH_SHORT).show()
                return false
            }
        }

        override fun onPostExecute(result: Boolean?) {
            super.onPostExecute(result)
            if (!result!!) Toast.makeText(this@DrinkActivity,"Database unavailable", Toast.LENGTH_SHORT).show()

        }
    }
}
