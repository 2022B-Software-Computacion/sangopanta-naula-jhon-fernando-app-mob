package com.example.entidades

import kotlinx.serialization.Serializable

@Serializable
data class User(var idUser: Int, var name: String, var lastName: String, var age: Int, var email: String, var posts: List<Post>? = null) {

    override fun toString(): String {
        return "${this.idUser},${this.name},${this.lastName},${this.age},${this.email},${this.posts}"
    }
}