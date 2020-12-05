package com.example.desafioandroidapis.model

import java.io.Serializable


class Comic(
    val id: Int?,
    val title: String?,
    val issueNumber: Int?,
    val description: String?,
    val pageCount: Int?,
    val thumbnail: Image?,
    val images: List<Image>?,
    val dates: List<ComicDate>?,
    val prices: List<ComicPrice>?,
) : Serializable {

    fun findValidImages(): List<Image> {
        val validImages = mutableListOf<Image>()

        if (thumbnail != null && thumbnail.isValid()) {
            validImages.add(thumbnail)
        }
        images?.forEach {
            if (it.isValid()) {
                validImages.add(it)
            }
        }

        return validImages
    }

    fun isValid(): Boolean = id != null && hasDescriptionAndTitle() && hasAtLeastOneImage()

    private fun hasDescriptionAndTitle(): Boolean = title != null && description != null

    private fun hasAtLeastOneImage(): Boolean {
        if (thumbnail != null && thumbnail.isValid()) {
            return true
        }
        return images != null && images.any { it.isValid() }
    }

    fun getFormatedPrice(default: String): String {
        if (prices == null) {
            return default
        }
        return "$${prices[0].getFormatedPrice(default)}"
    }

    fun getFormatedPublishDate(default: String): String {
        if (dates == null) {
            return default
        }
        return (dates.find { it.type == "focDate" } ?: dates[0]).getFormatedDate().toString()
    }

    fun getFormatedPageCount(default: String): String {
        return pageCount?.toString() ?: default
    }

    override fun toString(): String {
        return "$id, '$title', $issueNumber, '$description', $thumbnail, $images"
    }
}

