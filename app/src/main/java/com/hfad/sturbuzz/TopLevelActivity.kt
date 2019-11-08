package com.hfad.sturbuzz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_top_level.*

class TopLevelActivity:Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_level)

        list_options.setOnItemClickListener { parent, view, position, id ->
            if (position==0){
                val i= Intent(this,DrinkCategoryActivity::class.java)
                startActivity(i)
            }
        }
    }
}