package com.example.examen_2do_parcial.activities

import com.example.examen_2do_parcial.activities.ActualizarInformacion
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_2do_parcial.R
import com.example.examen_2do_parcial.adapters.UserAdapter
import com.example.examen_2do_parcial.crud.UserCRUD

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

       val userCRUD = UserCRUD()


        /* Inicializar el recycler view */
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_user)
        recyclerView.layoutManager = LinearLayoutManager(this)
        userCRUD.read {
            recyclerView.adapter = UserAdapter(it)
        }

        /* Inicializar el boton para agregar un usuario */
        val buttonAddUser = findViewById<Button>(R.id.btn_crear_usuario)
        buttonAddUser.setOnClickListener {
            goActivityGeneric(ActualizarInformacion::class.java)
        }
    }

    /* Funcion para cambiar de actividad */
    fun goActivityGeneric(activity: Class<*>) {
        val intentExplicito = Intent(
            this,
            activity
        )
        this.startActivity(intentExplicito)
    }



}