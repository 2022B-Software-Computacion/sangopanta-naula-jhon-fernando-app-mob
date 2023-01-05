package com.example.controller

interface AbstractController<T, ID> {
    fun delete(id: ID, path: String)
    fun create(Entity: T, path: String): Boolean
    fun update(Entity: T)
    fun read(): List<T>
    fun getById(id: ID): T?
    fun verifyId(id: Int): Boolean
    fun arrayToString(arr: List<T>): String

}