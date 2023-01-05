package com.example.a02_android

class BEntrenador(
    val nombre:String?,
    val descripcion:String?
) {
    override fun toString(): String {
        return "${nombre} - ${descripcion}"
    }
}