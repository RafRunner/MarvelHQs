package com.example.desafioandroidapis.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioandroidapis.model.Comic
import com.example.desafioandroidapis.services.ComicService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.coroutines.launch


class ComicDisplayViewModel(private val comicService: ComicService) : ViewModel() {

    val listComics = MutableLiveData<List<Comic>>()

    fun populateComicList(context: Context) {
        viewModelScope.launch {
            val validComicsFecthed = mutableListOf<Comic>()
            var offSet = 0

            while (true) {
                val response = comicService.getAllComics(offSet, 10)

                val results = response.get("data").asJsonObject.get("results")
                val comics = Gson().fromJson<List<Comic>>(
                    results,
                    object : TypeToken<List<Comic>>() {}.type
                )
                val validComics = comics.filter { it.isValid() }

                validComicsFecthed.addAll(validComics)

                if (validComicsFecthed.size >= 10) {
                    validComicsFecthed.forEach { loadComicImages(context, it) }
                    listComics.value = validComicsFecthed
                    break
                }

                offSet += 10
            }
        }
    }

    private fun loadComicImages(context: Context, comic: Comic) {
        comic.findValidImages().forEach {
            Picasso.with(context)
                .load(it.buildUrl())
                .into(object : Target {
                    override fun onBitmapFailed(errorDrawable: Drawable?) {
                    }

                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    }

                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        comic.imagesBitMap.add(bitmap)
                    }
                })
        }
    }
}