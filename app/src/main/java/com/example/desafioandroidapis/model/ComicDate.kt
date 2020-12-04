package com.example.desafioandroidapis.model

import java.time.LocalDate

class ComicDate(
    val type: String?,
    val date: String?,
) {
    fun isValid(): Boolean = type != null && date != null

    fun getFormatedDate(): LocalDate? {
        if (date == null) return null

        val values = Regex("(\\d{4})-(\\d{2})-(\\d{2})").find(date) ?: return null
        val groups = values.groups
        val year = groups[1]?.value?.toInt() ?: return null
        val month = groups[2]?.value?.toInt() ?: return null
        val day = groups[3]?.value?.toInt() ?: return null

        return LocalDate.of(year, month, day)
    }
}