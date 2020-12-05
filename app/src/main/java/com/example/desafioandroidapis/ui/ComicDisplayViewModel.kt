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
import retrofit2.HttpException


class ComicDisplayViewModel(private val comicService: ComicService) : ViewModel() {

    val listComics = MutableLiveData<List<Comic>>()

    private var numberOffsetsToApplay = 0

    fun fetchMoreComics() {
        val batchSize = 12

        viewModelScope.launch {
            val validComicsFecthed = mutableListOf<Comic>()

            while (true) {
                val response = try {
                    comicService.getAllComics(numberOffsetsToApplay * batchSize, batchSize)
                } catch (e: HttpException) {
                    Log.e("ComicDisplayViewModel", "Erro ao buscar comics: " + e.message())
                    return@launch
                }

                numberOffsetsToApplay++

                val results = response.get("data").asJsonObject.get("results")
                val comics = Gson().fromJson<List<Comic>>(results, object : TypeToken<List<Comic>>() {}.type)
                val validComics = comics.filter { it.isValid() }

                validComicsFecthed.addAll(validComics)

                if (validComicsFecthed.size >= batchSize) {
                    listComics.value = validComicsFecthed
                    break
                }
            }
        }
    }
}