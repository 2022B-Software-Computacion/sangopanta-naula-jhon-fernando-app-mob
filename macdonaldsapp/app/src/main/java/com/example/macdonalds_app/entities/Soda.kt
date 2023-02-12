package com.example.macdonalds_app.entities

import android.graphics.drawable.Drawable

data class Soda(
    override val id: Int,
    override val name: String,
    override val price: Double,
    override val image: Int
) :GenericProduct {

}