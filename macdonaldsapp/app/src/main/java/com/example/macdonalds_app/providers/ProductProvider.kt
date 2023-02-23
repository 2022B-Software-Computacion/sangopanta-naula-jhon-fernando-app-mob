package com.example.macdonalds_app.providers

import com.example.macdonalds_app.R
import com.example.macdonalds_app.entities.Hamburguer

class ProductProvider {
    companion object {
        val hambuguerList = listOf<Hamburguer>(
            Hamburguer(1,"Hamburguesa Doble",13.5,R.drawable.img),
            Hamburguer(1,"Hamburguesa Completa",17.5, R.drawable.img),
            Hamburguer(1,"Hamburguesa Doble",13.5,R.drawable.img),
            Hamburguer(1,"Hamburguesa Completa",17.5, R.drawable.img),
            Hamburguer(1,"Hamburguesa Doble",13.5,R.drawable.img),
            Hamburguer(1,"Hamburguesa Completa",17.5, R.drawable.img),
            Hamburguer(1,"Hamburguesa Doble",13.5,R.drawable.img),
            Hamburguer(1,"Hamburguesa Completa",17.5, R.drawable.img),
            Hamburguer(1,"Hamburguesa Doble",13.5,R.drawable.img),
            Hamburguer(1,"Hamburguesa Completa",17.5, R.drawable.img ),
        )
        val hambuguerListPopulares = listOf<Hamburguer>(
            Hamburguer(1,"Hamburguesa Doble",13.5,R.drawable.frech_fries),
            Hamburguer(1,"Hamburguesa Doble",13.5,R.drawable.frech_fries),
            Hamburguer(1,"Hamburguesa Doble",13.5,R.drawable.frech_fries),
            Hamburguer(1,"Hamburguesa Doble",13.5,R.drawable.frech_fries),
            )
        val hambuguerListPostres = listOf<Hamburguer>(
            Hamburguer(1,"Hamburguesa Doble",13.5,R.drawable.cono_chocolate),
            Hamburguer(1,"Hamburguesa Doble",13.5,R.drawable.cruhnch),
            Hamburguer(1,"Hamburguesa Doble",13.5,R.drawable.cruhnch),
            Hamburguer(1,"Hamburguesa Doble",13.5,R.drawable.cruhnch),
        )
    }
}