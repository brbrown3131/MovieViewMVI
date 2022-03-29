package com.connriverlines.movieviewmvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // get the movie info
        val name = intent.getStringExtra("name")
        val category = intent.getStringExtra("category")
        val desc = intent.getStringExtra("desc")
        val imgUrl = intent.getStringExtra("imgUrl")

        // this will reload everything on rotate but not worth the ViewModel
        tvName.text = name
        tvCategory.text = category
        tvDesc.text = desc

        if (imgUrl != null) {
            val utils = Utils()
            utils.loadImage(this, imageView, imgUrl)
        }
    }
}