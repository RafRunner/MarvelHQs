package com.example.desafioandroidapis.model

import java.io.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val dateFormat = DateTimeFormatter.ofPattern("MMMM dd, yyyy")

class ComicDate(
    val type: String?,
    private val date: String?,
) : Serializable {

    fun isValid(): Boolean = type != null && date != null

    fun getFormatedDate(): String? {
        if (date == null) return null

        val values = Regex("(\\d{4})-(\\d{2})-(\\d{2})").find(date)
        val groups = values!!.groups
        val year = groups[1]!!.value.toInt()
        val month = groups[2]!!.value.toInt()
        val day = groups[3]!!.value.toInt()

        return LocalDate.of(year, month, day).format(dateFormat)
    }
}