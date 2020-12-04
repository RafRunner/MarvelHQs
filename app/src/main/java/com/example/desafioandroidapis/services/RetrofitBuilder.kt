package com.example.desafioandroidapis.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun buildRetrofit(baseUrl: String = "https://gateway.marvel.com/v1/public/"): Retrofit =
    Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
