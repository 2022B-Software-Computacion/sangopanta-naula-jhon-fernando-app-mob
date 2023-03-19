package com.example.examen_2do_parcial.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.examen_2do_parcial.R
import com.example.examen_2do_parcial.crud.UserCRUD
import com.example.examen_2do_parcial.entities.User

class ActualizarInformacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_informacion)

        val email_et = findViewById<EditText>(R.id.et_correo)
        val age_et = findViewById<EditText>(R.id.et_edad)
        val lastName = findViewById<EditText>(R.id.et_apellido)
        val name = findViewById<EditText>(R.id.et_nombre)
        val botonCrear = findViewById<Button>(R.id.btn_actualizar_informacion)

        // Actualizar usuario

        val extras: Bundle? = intent.extras
        if (extras != null) {
            /* Recibimos los datos enviados desde la actividad anterior */

            val id = extras.getString("id")
            val nombreCompleto = extras.getString("name")
            val nombresComletos = nombreCompleto?.split(" ")
            val nombre = nombresComletos?.get(0)
            var apellido = nombresComletos?.get(1).toString()
            val age = extras.getString("age")
            val email = extras.getString("email")

            /* Inicializamos todos los campos de texto con los datos recibidos */

            name.setText(nombre)
            lastName.setText(apellido)
            age_et.setText(age)
            email_et.setText(email)

            botonCrear.setOnClickListener {
                val user: User = User()
                user.idUser = id.toString()
                user.name = name.text.toString()
                user.lastName = lastName.text.toString()
                user.age = age_et.text.toString().toInt()
                user.email = email_et.text.toString()

                UserCRUD().update(user) {
                    goActivityGeneric(UserActivity::class.java)
                }
            }
        } else {

            botonCrear.setOnClickListener {
                val user: User = User()
                user.name = name.text.toString()
                user.lastName = lastName.text.toString()
                user.age = age_et.text.toString().toInt()
                user.email = email_et.text.toString()
                UserCRUD().create(user) {
                    goActivityGeneric(UserActivity::class.java)
                }
            }

        }

        val regresar = findViewById<Button>(R.id.btn_regresar_listUsuarios)

        regresar.setOnClickListener {
            goActivityGeneric(UserActivity::class.java)
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
