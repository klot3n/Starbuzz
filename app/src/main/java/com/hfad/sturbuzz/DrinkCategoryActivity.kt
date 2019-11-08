package com.hfad.sturbuzz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_drink_category.*

class DrinkCategoryActivity:Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_category)

        list_drinks.adapter = ArrayAdapter<Drink>(
            this,
            android.R.layout.simple_list_item_1,
            Drink.drinks)
        list_drinks.setOnItemClickListener { parent, view, position, id ->
            val i=Intent(this,DrinkActivity::class.java)
            i.putExtra(/*DrinkActivity.EXTRA_DRINKID*/"EXTRA_DRINKID",id)
            startActivity(i)
        }
    }

}
