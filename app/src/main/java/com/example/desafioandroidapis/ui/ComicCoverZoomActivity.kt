package com.example.desafioandroidapis.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import com.example.desafioandroidapis.R
import com.example.desafioandroidapis.model.Comic
import com.squareup.picasso.Picasso


class ComicCoverZoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_cover_zoom)

        (intent.getSerializableExtra("comic") as? Comic)?.let { comic ->
            Picasso.with(this)
                .load(comic.findValidImages()[0].buildUrl())
                .fit()
                .into(findViewById<ImageView>(R.id.ivComicCover))
        }

        findViewById<ImageButton>(R.id.btnClose).setOnClickListener {
            onBackPressed()
        }
    }
}