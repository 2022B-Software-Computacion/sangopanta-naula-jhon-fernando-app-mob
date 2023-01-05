package com.example.entities

import kotlinx.serialization.Serializable

@Serializable
data class Post(val idPost: Int, val date: String, val description: String, val idUser: Int) {

    override fun toString(): String {
        return "${this.idPost},${this.date},${this.description},${this.idUser}"
    }

}