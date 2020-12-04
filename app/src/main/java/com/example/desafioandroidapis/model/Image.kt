package com.example.desafioandroidapis.model

class Image(
    val path: String?,
    val extension: String?,
) {
    fun isValid(): Boolean =
        path != null && !path.endsWith("image_not_available") && extension != null

    override fun toString(): String {
        return "Image(path='$path', extension='$extension')"
    }
}