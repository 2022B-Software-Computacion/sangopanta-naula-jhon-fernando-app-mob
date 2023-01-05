package com.example.entities

import kotlinx.serialization.Serializable

@Serializable
data class User(val idUser: Int, val name: String, val lastName: String, var posts: List<Post>?) {

    override fun toString(): String {
        return "${this.idUser},${this.name},${this.lastName},${this.posts}"
    }

}