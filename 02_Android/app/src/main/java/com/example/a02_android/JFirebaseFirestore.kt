package com.example.a02_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList


class JFirebaseFirestore : AppCompatActivity() {

    var query: Query? = null
    val arreglo: ArrayList<JCitiesDTO> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jfirebase_firestore)

        val listView = findViewById<ListView>(R.id.lv_firestore)

        val adaptador: ArrayAdapter<JCitiesDTO> = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // Layout
            arreglo // Arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonPrueba = findViewById<Button>(R.id.btn_fs_datos_prueba)
        botonPrueba.setOnClickListener {
            crearDatosPrueba()
        }

        val botonOrderBy = findViewById<Button>(R.id.btn_fs_order_by)
        botonOrderBy
            .setOnClickListener {
                consultarConOrderBy(adaptador)
            }

        val botonObtenerDocumento = findViewById<Button>(R.id.btn_fs_odoc)
        botonObtenerDocumento
            .setOnClickListener {
                consultarDocumento(adaptador)
            }

        val botonFirebaseIndiceCompuesto = findViewById<Button>(R.id.btn_fs_ind_comp)
        botonFirebaseIndiceCompuesto
            .setOnClickListener {
                consultarIndiceCompuesto(adaptador)
            }

        val botonFirebaseCrear = findViewById<Button>(R.id.btn_fs_crear)
        botonFirebaseCrear
            .setOnClickListener {
                crearDatosEjemplo()
            }

        val botonFirebaseEliminar = findViewById<Button>(R.id.btn_fs_eliminar)
        botonFirebaseEliminar
            .setOnClickListener {
                eliminarRegistros()
            }

        val botonFirebaseEmpezarPaginar = findViewById<Button>(R.id.btn_fs_epaginar)
        botonFirebaseEmpezarPaginar
            .setOnClickListener {
                query = null;
                consultarCiudades(adaptador)
            }

        val botonFirebasePaginar = findViewById<Button>(R.id.btn_fs_paginar)
        botonFirebasePaginar
            .setOnClickListener {
                consultarCiudades(adaptador)
            }

    }

    fun consultarCiudades(
        adaptador: ArrayAdapter<JCitiesDTO>
    ) {
        val db = Firebase.firestore // Objeto del firestore
        val citiesRef = db
            .collection("cities") // Nombre de la coleccion
            .orderBy("population")
            .limit(1)

        var tarea: Task<QuerySnapshot>? = null

        if (query == null) {
            tarea = citiesRef.get() // 1era vez
            limpiarArreglo()
            adaptador.notifyDataSetChanged()
        } else {
            tarea = query!!.get() // Consulta de la anterior empezando desde el nuevo documento
        }

        if (tarea != null) {
            tarea
                .addOnSuccessListener { documentSnapshots ->
                    guardarQuery(documentSnapshots, citiesRef)
                    for (ciudad in documentSnapshots) {
                        añadirArregloCiudad(arreglo, ciudad, adaptador)
                    }
                    adaptador.notifyDataSetChanged()
                }
                .addOnFailureListener {
                    // Si algo salio mal
                }
        }
    }

    fun guardarQuery(
        documentSnapshots: QuerySnapshot,
        citiesRef: Query
    ) {
        if (documentSnapshots.size() > 0) {
            val ultimoDocumento = documentSnapshots.documents[documentSnapshots.size() - 1]
            query = citiesRef
                .startAfter(ultimoDocumento)
        }
    }

    // [1,2,3,4,5,6,7]
    // 4 Primeros [1,2,3,4]
    // [1,3,5,6,7,10,12]
    // 4 Primeros [1,3,5,6]
    // 4 Siguientes  6 => [7,10,12]
    fun eliminarRegistros() {
        val db = Firebase.firestore // Objeto del firestore
        // Recuperamos la referencia que vamos a eliminar
        val referenciaEjemploEstudiante = db
            .collection("ejemplo")
            .document("1234567")
            .collection("Estudiante")

        // Eliminamos el documento con el identificador 123456789
        referenciaEjemploEstudiante
            .document("123456789")
            .delete() // Se eliminan los datos
            .addOnCompleteListener { /* Si todo salio bien*/ }
            .addOnFailureListener { /* Si algo salio mal*/ }
    }

    fun crearDatosEjemplo(
    ) {
        val db = Firebase.firestore // Objeto del firestore

        val referenciaEjemploEstudiante = db
            .collection("ejemplo")
            .document("1234567")
            .collection("estudiante")

        val identificador = Date().time
        val datosEstudiante = hashMapOf(
            "nombre" to "Fernando",
            "graduado" to false,
            "promedio" to 14.00,
            "direccion" to hashMapOf(
                "direccion" to "Mitad del mundo",
                "numeroCalle" to 1234
            ),
            "materias" to listOf("web", "mobiles")
        )

        /* Con identificador quemado*/
        referenciaEjemploEstudiante
            .document("1234567")
            .set(datosEstudiante) // Actualiza o crea
            .addOnCompleteListener { /* Si todo salio bien*/ }
            .addOnFailureListener { /* Si algo salio mal*/ }

        /* Con identificador generado en Date.time*/
        referenciaEjemploEstudiante
            .document(identificador.toString())
            .set(datosEstudiante) // Actualiza o crea
            .addOnCompleteListener { /* Si todo salio bien*/ }
            .addOnFailureListener { /* Si algo salio mal*/ }

        /* Sin identificador */
        referenciaEjemploEstudiante
            .add(datosEstudiante) // Crea
            .addOnCompleteListener { /* Si todo salio bien*/ }
            .addOnFailureListener { /* Si algo salio mal*/ }
    }

    fun consultarIndiceCompuesto(
        adaptador: ArrayAdapter<JCitiesDTO>
    ) {
        val db = Firebase.firestore // Objeto del firestore
        val citiesRefUnico = db
            .collection("cities") // Nombre de la coleccion
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        citiesRefUnico
            .whereEqualTo("capital", false)
            .whereLessThan("population", 4000000)
            .orderBy("population", com.google.firebase.firestore.Query.Direction.ASCENDING)
            .get() // Obtenemos la petición
            .addOnSuccessListener { result ->
                for (ciudad in result) {
                    añadirArregloCiudad(arreglo, ciudad, adaptador)
                }
            }
    }


    fun consultarDocumento(
        adaptador: ArrayAdapter<JCitiesDTO>
    ) {
        val db = Firebase.firestore // Objeto del firestore
        val citiesRefUnico = db
            .collection("cities") // Nombre de la coleccion
        //  /cities/BJ (1 documento )
        citiesRefUnico
            .document("BJ")
            .get()
            .addOnSuccessListener {
                it.id  // BJ // Obtenemos el id de firestore
                limpiarArreglo()
                arreglo.add(
                    JCitiesDTO(
                        it.data?.get("name") as String?,
                        it.data?.get("state") as String?,
                        it.data?.get("country") as String?,
                        it.data?.get("capital") as Boolean?,
                        it.data?.get("population") as Long?,
                        it.data?.get("regions") as ArrayList<String>?
                    )
                )
                adaptador.notifyDataSetChanged()
            }
    }

    fun consultarConOrderBy(
        adaptador: ArrayAdapter<JCitiesDTO>
    ) {
        val db = Firebase.firestore // Objeto del firestore
        val citiesRefUnico = db
            .collection("cities") // Nombre de la coleccion
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        citiesRefUnico
            // No usamos con document porque en document nos devuelve uno solo
            // cities => "Population" ASCENDING
            .orderBy("population", Query.Direction.ASCENDING)
            .get() // Obtenemos la petición
            .addOnSuccessListener {
                for (ciudad in it) {
                    ciudad.id
                    añadirArregloCiudad(arreglo, ciudad, adaptador)
                }
            }
    }

    fun crearDatosPrueba() {
        val db = Firebase.firestore // Objeto del firestore


        val cities = db.collection("cities") // Nombre de la coleccion


        val data1 = hashMapOf( // Objeto a guardarse
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal")
        )
        cities.document("SF") // Asignamos el id SF
            .set(data1) //


        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal")
        )
        cities.document("LA").set(data2)


        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast")
        )
        cities.document("DC").set(data3)


        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu")
        )
        cities.document("TOK").set(data4)


        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei")
        )
        cities.document("BJ").set(data5)
    }

    fun limpiarArreglo() {
        arreglo.clear()
    }

    fun añadirArregloCiudad(
        arregloNuevo: ArrayList<JCitiesDTO>,
        ciudad: QueryDocumentSnapshot,
        adaptador: ArrayAdapter<JCitiesDTO>
    ) {
        ciudad.id // Obtenemos el id de firestore
        val nuevaCiudad = JCitiesDTO(
        ciudad.data.get("name") as String?,
        ciudad.data.get("state") as String?,
        ciudad.data.get("country") as String?,
        ciudad.data.get("capital") as Boolean?,
        ciudad.data.get("population") as Long?,
        ciudad.data.get("regions") as ArrayList<String>
        )
        arregloNuevo.add(
            nuevaCiudad
        )
        adaptador.notifyDataSetChanged()
    }
}