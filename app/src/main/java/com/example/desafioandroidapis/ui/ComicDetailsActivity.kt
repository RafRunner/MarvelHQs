package com.example.desafioandroidapis.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.example.desafioandroidapis.R
import com.example.desafioandroidapis.model.Comic

class ComicDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_details)

        (intent.getSerializableExtra("comic") as? Comic)?.let { comic ->
            findViewById<TextView>(R.id.tvComicTitle).text       = comic.title
            findViewById<TextView>(R.id.tvComicDescription).text = comic.description

            val default = resources.getString(R.string.not_available)

            findViewById<TextView>(R.id.tvComicPrice).apply {
                text = text.toString().format(comic.getFormatedPrice(default))
            }
            findViewById<TextView>(R.id.tvComicPublishDate).apply {
                text = text.toString().format(comic.getFormatedPublishDate(default))
            }
            findViewById<TextView>(R.id.tvComicPages).apply {
                text = text.toString().format(comic.getFormatedPageCount(default))
            }
        }

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            onBackPressed()
        }
    }
}