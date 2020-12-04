package com.example.desafioandroidapis.model

class ComicPrice(
    val type: String?,
    val price: Float?,
) {
    fun isValid(): Boolean = type != null && price != null
}