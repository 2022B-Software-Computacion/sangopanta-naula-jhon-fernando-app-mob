package com.example.examen_2do_parcial.entities

class Post(
    var idPost: String = "",
    var title: String,
    var description: String,
    var date: String,
    var category: String,
    var idUser: String
) {

    constructor() : this("", "", "", "", "", "")
}