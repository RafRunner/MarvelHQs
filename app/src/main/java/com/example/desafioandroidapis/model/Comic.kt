package com.example.desafioandroidapis.model

//TODO pegar o atributo images (tem que criar outra classe)
class Comic(
    val id: Int,
    val title: String,
    val issueNumber: Int,
    val description: String,
) {
    override fun toString(): String {
        return "$id, '$title', $issueNumber, '$description'"
    }
}