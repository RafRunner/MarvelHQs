package com.example.desafioandroidapis.services

import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicService {

    @GET("comics")
    suspend fun getAllComics(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("format") format: String = "comic",
        @Query("ts") ts: String = "1",
        @Query("apikey") apikey: String = "6eb7e8896ec5850c52515a8a23ee97f0",
        @Query("hash") hash: String = "40a3aa568bb269dfad85ae0c4a297181",
    ) : JsonObject
}

val retrofit = buildRetrofit()

val comicService: ComicService = retrofit.create(ComicService::class.java)
