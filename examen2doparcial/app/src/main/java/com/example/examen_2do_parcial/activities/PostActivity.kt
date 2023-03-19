package com.example.examen_2do_parcial.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_2do_parcial.R
import com.example.examen_2do_parcial.adapters.PostAdapter
import com.example.examen_2do_parcial.crud.PostCRUD


class PostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val postCRUD = PostCRUD()
        val botonCrearPost = findViewById<Button>(R.id.btn_crear_post)

        val idUser = intent.getStringExtra("id")

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_post)
        recyclerView.layoutManager = LinearLayoutManager(this)
        if (idUser != null) {
            postCRUD.readByUserId(idUser) { posts ->
                recyclerView.adapter = PostAdapter(posts)
            }
        }

        val regresar = findViewById<Button>(R.id.btn_regresar_User)
        regresar.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

        botonCrearPost.setOnClickListener {
            val intent = Intent(this, CrearPost::class.java)
            intent.putExtra("id",idUser)
            startActivity(intent)
        }
    }
}