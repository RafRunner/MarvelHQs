package com.example.desafioandroidapis.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafioandroidapis.model.Comic
import com.example.desafioandroidapis.services.ComicService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class ComicDisplayViewModel(private val comicService: ComicService) : ViewModel() {

    val listComics = MutableLiveData<List<Comic>>()

    fun populateComicList() {
        viewModelScope.launch {
            val response = comicService.getAllComics(0, 10)

            val results = response.get("data").asJsonObject.get("results")
            val comics = Gson().fromJson<List<Comic>>(results, object : TypeToken<List<Comic>>() {}.type)

            Log.i("ComicDisplayViewModel", comics.toString())
        }
    }
}