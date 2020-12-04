package com.example.desafioandroidapis.model

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
) {
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

    private fun hasAtLeastOneImage(): Boolean = findValidImages().isNotEmpty()

    override fun toString(): String {
        return "$id, '$title', $issueNumber, '$description', $thumbnail, $images"
    }
}

