package com.example.macdonalds_app.entities

data class FrechFries(
    override val id: Int,
    override val name: String,
    override val price: Double,
    override val image: Int
) : GenericProduct {
}