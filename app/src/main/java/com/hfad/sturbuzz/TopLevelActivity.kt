package com.hfad.sturbuzz

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.CursorAdapter
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_top_level.*

class TopLevelActivity:Activity() {

    lateinit var db: SQLiteDatabase
    lateinit var favoritesCursor:Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_level)
        setupOptionsListView()
        setupFavoritesListView()
    }

    fun setupOptionsListView(){
        list_options.setOnItemClickListener { parent, view, position, id ->
            if (position==0){
                val i= Intent(this,DrinkCategoryActivity::class.java)
                startActivity(i)
            }
        }
    }

    fun setupFavoritesListView(){
        try {
            val starbuzzDatabaseHelper=StarbuzzDatabaseHelper(this)
            db=starbuzzDatabaseHelper.readableDatabase
            favoritesCursor = db.run {
                query("DRINK", arrayOf("_id","NAME"),
                "FAVORITE=1",null,null,null,null)
            }
            list_favorites.adapter=SimpleCursorAdapter(
                this, android.R.layout.simple_list_item_1,
                favoritesCursor, arrayOf("NAME"), intArrayOf(android.R.id.text1), 0
            )
        } catch (e: Exception) {
            Toast.makeText(this, "Database unavialable", Toast.LENGTH_SHORT).show()
        }
        list_favorites.setOnItemClickListener { _, _, _, id ->
                val i = Intent(this, DrinkActivity::class.java)
                i.putExtra("EXTRA_DRINKID",id)
                startActivity(i)
        }
    }

    override fun onRestart() {
        super.onRestart()
        val newCursor=db.query("DRINK", arrayOf("_id","NAME"),
            "FAVORITE=1",null,null,null,null)
        list_favorites.adapter
        val adapter:CursorAdapter= list_favorites.adapter as CursorAdapter
        adapter.changeCursor(newCursor)
        favoritesCursor=newCursor
    }

    override fun onDestroy() {
        super.onDestroy()
        favoritesCursor.close()
        db.close()
    }
}