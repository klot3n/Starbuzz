package com.hfad.sturbuzz

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_drink.*

class DrinkActivity :Activity(){

    var EXTRA_DRINKID ="drinkId"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink)
        var drinkId =intent.getLongExtra("EXTRA_DRINKID",213)
        var drink= Drink.drinks[drinkId.toInt()]
        photo.setImageResource(drink.imageResourceId)
        name.text=drink.name
        description.text=drink.description
    }

}
