package com.example.examen_2do_parcial.entities

class User(
    var idUser: String = "",
    var name: String = "",
    var lastName: String = "",
    var age: Int = 0,
    var email: String = ""
) {
    constructor() : this("", "", "", 0, "")
}

