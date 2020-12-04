package com.example.desafioandroidapis.model

class Image(
    val path: String?,
    val extension: String?,
) {
    fun isValid(): Boolean =
        path != null && !path.endsWith("image_not_available") && extension != null

    override fun toString(): String {
        return "$path.$extension"
    }

    fun buildUrl(): String = "$path.$extension"
}