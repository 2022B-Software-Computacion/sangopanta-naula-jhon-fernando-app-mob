package com.example.files

import java.io.*

class ManagementFiles {
    fun readFile(path: String): List<List<String>> {

        val arr: ArrayList<String> = ArrayList()
        val reader = BufferedReader(FileReader(path))
        var linea: String? = reader.readLine()
        while (linea != null) {
            arr.add(linea)
            linea = reader.readLine()
        }

        val post = arr.map { it -> it.split(",") }
        return post


    }

    fun writeFile(path: String, text: String, append: Boolean = true) {
        val file = File(path)
        val bw = BufferedWriter(FileWriter(file, append))
        bw.write(text)
        bw.close()
    }


}