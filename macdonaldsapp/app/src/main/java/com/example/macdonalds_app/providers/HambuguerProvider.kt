package com.example.macdonalds_app.providers

import com.example.macdonalds_app.R
import com.example.macdonalds_app.entities.Hamburguer

class HambuguerProvider {
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
            Hamburguer(1,"Hamburguesa Doble",13.5,R.drawable.img),
        )
    }
}