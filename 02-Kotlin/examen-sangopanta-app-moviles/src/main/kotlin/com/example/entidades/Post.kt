package com.example.entidades

import kotlinx.serialization.Serializable

@Serializable
data class Post(var idPost: Int, var title: String, var description: String, var date: String, var category:String, var idUser: Int) {

    override fun toString(): String {
        return "${this.idPost},${this.title},${this.description},${this.date},${this.category},${this.idUser}"
    }
}