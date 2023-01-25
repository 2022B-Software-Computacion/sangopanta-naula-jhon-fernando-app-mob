package com.example.a02_android

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class ESqliteHelperEntrenador (
        contexto: Context? //this
        ): SQLiteOpenHelper(
            contexto,
            "mobiles",
            null,
            1
        ){

    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun crearEntrenador(
        nombre:String,
        descripcion:String
    ): Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAguardar = ContentValues()
        valoresAguardar.put("nombre",nombre)
        valoresAguardar.put("descripcion",descripcion)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "Entrenador", // Nombre tabla
                      null,
                       valoresAguardar
             )
        basedatosEscritura.close()
        return resultadoGuardar.toInt() != 1
    }

    fun eliminarEntrenadorFormulario(id:Int): Boolean {
        val conexionEscritura = writableDatabase

        val resultadoEliminacion = conexionEscritura
            .delete(
                "ENTRENADOR", // Nombre de la tabla
                "id=?", //consulta where
                    arrayOf(
                        id.toString()
                    )
            )
        conexionEscritura.close()
        return resultadoEliminacion.toInt() != -1
    }

    fun actualizarEntrenadorFormulario(
        name:String,
        descripcion:String,
        idActualizar:Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()

        valoresActualizar.put("nombre",name)
        valoresActualizar.put("descripcion",descripcion)

        val resultadoActualizar = conexionEscritura
            .update(
                "ENTRENADOR", // Nombre tabla
                    valoresActualizar, //Valores a actualizar
                    "id?",
                    arrayOf(
                        idActualizar.toString()
                    )
            )
        conexionEscritura.close()
        return resultadoActualizar.toInt() == -1
    }

    fun  consultarEntrenadorPorId(id: Int):BEntrenador{
        val baseDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM ENTRENADOR WHERE ID = ?"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            arrayOf(
                id.toString()
            )
        )
        val  existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = BEntrenador(0, "","")
        val arreglo = arrayListOf<BEntrenador>()
        do{
            val id = resultadoConsultaLectura.getInt(0)
            val nombre = resultadoConsultaLectura.getString(1)
            val descripcion = resultadoConsultaLectura.getString(2)
            if(id != null){
                usuarioEncontrado.id = id
                usuarioEncontrado.nombre = nombre
                usuarioEncontrado.descripcion = descripcion
                arreglo.add(usuarioEncontrado)
            }
        }while (resultadoConsultaLectura.moveToNext())
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }
}