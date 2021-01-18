package com.example.desafioandroidapis.model

import java.io.Serializable

class ComicPrice(
    private val type: String?,
    private val price: Float?,
) : Serializable {

    fun isValid(): Boolean = type != null && price != null

    fun getFormatedPrice(default: String): String {
        return "%.2f".format(price)
    }
}