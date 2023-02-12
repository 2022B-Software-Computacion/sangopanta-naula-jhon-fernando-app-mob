package com.example.a02_android

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    val contenidoIntentExplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
                if (result.resultCode == Activity.RESULT_OK){
                    if(result.data !=null){
                        val data = result.data
                        Log.i("intent-epn","${data?.getStringExtra("nombreModificado")}")
                    }
                }
        }

    val contenidoIntentImplicito = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result ->
        if(result.resultCode == RESULT_OK){
            if(result.data != null){
               if(result.data!!.data != null){
                   val uri: Uri = result.data!!.data!!
                   val cursor = contentResolver.query(
                       uri,
                       null,
                       null,
                       null,
                       null,
                       null
                   )

                   cursor?.moveToFirst()
                   val indiceTelefono = cursor?.getColumnIndex(
                       ContactsContract.CommonDataKinds.Phone.NUMBER
                   )

                   val telefono = cursor?.getString(
                       indiceTelefono!!
                   )
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Base datos sqlite
        EBaseDeDatos.tablaEntrenador = ESqliteHelperEntrenador(this)

        val botonCicloVida = findViewById<Button>(R.id.button)
        botonCicloVida
            .setOnClickListener {
                irActividad(BListView::class.java)
            }

        val botonEBaseDeDatos = findViewById<Button>(R.id.btn_ir_dbb)
        botonEBaseDeDatos
            .setOnClickListener{
                irActividad(ECRUDEntrenador::class.java)
            }

        val botonIntentImplicito = findViewById<Button>(R.id.Implicito)
        botonIntentImplicito
            .setOnClickListener{
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                contenidoIntentImplicito.launch(intentConRespuesta)
            }

        val botonRView = findViewById<Button>(R.id.btn_recycler_view)

        botonRView.setOnClickListener{
            irActividad(GRecyclerView::class.java)
        }

        val botonMaps = findViewById<Button>(R.id.btn_google_maps)
        botonMaps.setOnClickListener{
            irActividad(HGoogleMapsActivity::class.java)
        }

        val botonFirebaseUI = findViewById<Button>(R.id.btn_intent_firebase)
        botonFirebaseUI
            .setOnClickListener {
                irActividad(IFirebaseUIAuth::class.java)
            }
    }



    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun abrirActividadConParametros(
        clase: Class<*>,
    ){
        val intentExplicito = Intent(this,clase)
        //Enviar parametros (solamente variables primitivas
        intentExplicito.putExtra("nombre","Adrian")
        intentExplicito.putExtra("apellido","Eguez")
        intentExplicito.putExtra("edad",33)

        intentExplicito.putExtra("entrenadorPrincipal",
                                   BEntrenador(4,"Adrian","Paleta") )

        contenidoIntentExplicito.launch(intentExplicito)
    }



}