package com.example.macdonalds_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.macdonalds_app.adapters.ProductAdapter
import com.example.macdonalds_app.entities.Hamburguer
import com.example.macdonalds_app.fragments.*
import com.example.macdonalds_app.providers.HambuguerProvider

import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fastFood = FastFoodFragment()
        val cart = CartFragment()
        val account = AccountFragment()


        /* Set Fast Food fragment when the app starts*/
        setCurrentFragment(fastFood)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        /* On click for each icon set a fragment */
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.fast_food -> {
                    setCurrentFragment(fastFood)
                    true
                }
                R.id.cart -> {
                    setCurrentFragment(cart)
                    true
                }
                R.id.account -> {
                    setCurrentFragment(account)
                    true
                }
                else -> {
                    false
                }

            }
        }



    }

    /* Function that set a fragment */
    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container_view,fragment)
            commit()
        }
    }
}