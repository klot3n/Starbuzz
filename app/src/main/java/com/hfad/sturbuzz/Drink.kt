package com.hfad.sturbuzz

class Drink(var name: String, var description: String, var imageResourceId: Int) {
    companion object {
        val drinks = arrayOf(
            Drink("Latte","A couple of espresso shots with steamed milk",R.drawable.latte),
            Drink("Cappuccino","Espresso,hot milk, and a steamed milk foam", R.drawable.cappuccino),
            Drink("Filter","Highest quality beans roasted and brewed fresh", R.drawable.filter)
        )
    }
}