package com.example.examen_2do_parcial.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.examen_2do_parcial.R
import com.example.examen_2do_parcial.crud.PostCRUD
import com.example.examen_2do_parcial.entities.Post

class CrearPost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_post)

        val title_et = findViewById<EditText>(R.id.et_title)
        val description_et = findViewById<EditText>(R.id.et_description)
        val date_et = findViewById<EditText>(R.id.et_date)
        val category_et = findViewById<EditText>(R.id.et_category)
        val botonActualizar = findViewById<Button>(R.id.btn_actualizar_informacion_post)
       // Recuperamos el id
        val idUser = intent.getStringExtra("id")

        val postCRUD = PostCRUD()

        botonActualizar.setOnClickListener {
            val title = title_et.text.toString()
            val description = description_et.text.toString()
            val date = date_et.text.toString()
            val category = category_et.text.toString()

            val post = Post(
                title = title,
                description = description,
                date = date,
                category = category,
                idUser = idUser!!
            )
            postCRUD.createByUserId(idUser, post) {
                val intentPost = Intent(this, PostActivity::class.java)
                intentPost.putExtra("id", idUser)
                startActivity(intentPost) // iniciamos la actividad con el intent que contiene el idUser
            }

        }

        val regresar = findViewById<Button>(R.id.btn_regresar_listPosts)
        regresar.setOnClickListener {
            val intentPost = Intent(this, PostActivity::class.java)
            intentPost.putExtra("id", idUser)
            startActivity(intentPost) // iniciamos la actividad con el intent que contiene el idUser
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