package com.example.a02_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class IFirebaseUIAuth : AppCompatActivity() {

    // Callback del INTENT  de LOGIN
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ){ res: FirebaseAuthUIAuthenticationResult->
        if(res.resultCode === RESULT_OK){
            if(res.idpResponse != null){
                // Logica del negocio
                seLogeo(res.idpResponse!!)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ifirebase_uiauth)
        val btnLogin = findViewById<Button>(R.id.btn_login_firebase)
        btnLogin
            .setOnClickListener {
                val providers = arrayListOf(
                    // Arreglo de PROVIDERS para logease
                    //Ej: Correo, Facebook, Twitter, Google
                    AuthUI.IdpConfig.EmailBuilder().build()
                )

                // Create and launch sign-in intent
                val signInIntent = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build()
                // Respuesta del INTENT del Login
                signInLauncher.launch(signInIntent)
            }
        val btnLogout = findViewById<Button>(R.id.btn_logout_firebase)
        btnLogout
            .setOnClickListener {
                seDeslogeo()
            }
    }

    fun seLogeo(res: IdpResponse ){
        val btnLogin = findViewById<Button>(R.id.btn_login_firebase)
        val btnLogOut = findViewById<Button>(R.id.btn_logout_firebase)
        btnLogin.visibility = View.VISIBLE
        btnLogOut.visibility = View.VISIBLE

        if(res.isNewUser){
            registrarUsuarioPorPrimeraVez(res)
        }
    }

    fun registrarUsuarioPorPrimeraVez(
        usuario:IdpResponse
    ){

    }

    fun seDeslogeo(){

        val btnLogin = findViewById<Button>(R.id.btn_login_firebase)
        val btnLogout = findViewById<Button>(R.id.btn_logout_firebase)

        btnLogin.visibility = View.INVISIBLE
        btnLogout.visibility = View.VISIBLE

        FirebaseAuth.getInstance().signOut()

    }

}