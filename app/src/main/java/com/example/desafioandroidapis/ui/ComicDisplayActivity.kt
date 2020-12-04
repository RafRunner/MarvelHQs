package com.example.desafioandroidapis.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.desafioandroidapis.R
import com.example.desafioandroidapis.services.comicService

class ComicDisplayActivity : AppCompatActivity() {

    private val comicDisplayViewModel by viewModels<ComicDisplayViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ComicDisplayViewModel(comicService) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_display)

        comicDisplayViewModel.listComics.observe(this) {
            Log.i("ComicDisplayActivity", it.toString())
        }

        comicDisplayViewModel.populateComicList()
    }
}