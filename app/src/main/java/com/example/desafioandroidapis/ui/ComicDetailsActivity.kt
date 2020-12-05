package com.example.desafioandroidapis.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.desafioandroidapis.R
import com.example.desafioandroidapis.model.Comic
import com.squareup.picasso.Picasso


class ComicDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_details)

        (intent.getSerializableExtra("comic") as? Comic)?.let { comic ->
            findViewById<TextView>(R.id.tvComicTitle).text = comic.title
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

            findViewById<ImageView>(R.id.ivComicCover).setOnClickListener {
                val intent = Intent(this, ComicCoverZoomActivity::class.java)
                intent.putExtra("comic", comic)
                startActivity(intent)
            }

            val images = comic.findValidImages()
            val indexBannerImage = if (images.size == 1) 0 else 1

            Picasso.with(this)
                .load(images[0].buildUrl())
                .fit()
                .into(findViewById<ImageView>(R.id.ivComicCover))

            Picasso.with(this)
                .load(images[indexBannerImage].buildUrl())
                .resize(600, 600)
                .centerCrop()
                .into(findViewById<ImageView>(R.id.ivComicBanner))
        }

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            onBackPressed()
        }
    }
}