package com.example.crud

interface AbstractCRUD<T, ID> {
    fun delete(id: ID, path: String)
    fun create(entity: T, path: String): Boolean
    fun update(entity: T, id: ID) : Boolean
    fun read(): List<T>
    fun getById(id: ID): T?
    fun verifyId(id: Int): Boolean
    fun arrayToString(arr: List<T>): String
}