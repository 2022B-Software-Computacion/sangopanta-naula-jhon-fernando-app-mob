package com.example.examen_2do_parcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.examen_2do_parcial.activities.UserActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainExecutor: Handler = Handler(Looper.getMainLooper())
        mainExecutor.postDelayed({
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

}