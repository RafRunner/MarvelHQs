package com.example.desafioandroidapis.model

import java.io.Serializable

class Image(
    val path: String?,
    val extension: String?,
) : Serializable {
    fun isValid(): Boolean =
        path != null && !path.endsWith("image_not_available") && extension != null

    override fun toString(): String {
        return "$path.$extension"
    }

    fun buildUrl(): String = "$path.$extension"
}