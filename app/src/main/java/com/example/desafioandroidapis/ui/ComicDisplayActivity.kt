package com.example.desafioandroidapis.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroidapis.R
import com.example.desafioandroidapis.model.Comic
import com.example.desafioandroidapis.services.comicService

class ComicDisplayActivity : AppCompatActivity() {

    private val comicDisplayViewModel by viewModels<ComicDisplayViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ComicDisplayViewModel(comicService) as T
            }
        }
    }

    private val comicDisplayAdapter = ComicDisplayAdapter(this, ::callComicDetails)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_display)

        val self = this

        findViewById<RecyclerView>(R.id.rvComics).apply {
            adapter = comicDisplayAdapter
            layoutManager = GridLayoutManager(self, 3)
            setHasFixedSize(false)
        }

        comicDisplayViewModel.listComics.observe(self) {
            comicDisplayAdapter.setComicList(it)
        }

        comicDisplayViewModel.populateComicList()
    }

    private fun callComicDetails(comic: Comic) {
        Toast.makeText(this, comic.title, Toast.LENGTH_LONG).show()
    }
}