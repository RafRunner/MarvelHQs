package com.example.desafioandroidapis.model

import java.io.Serializable

class ComicPrice(
    val type: String?,
    val price: Float?,
) : Serializable {

    fun isValid(): Boolean = type != null && price != null

    fun getFormatedPrice(default: String): String {
        return "%.2f".format(price)
    }
}