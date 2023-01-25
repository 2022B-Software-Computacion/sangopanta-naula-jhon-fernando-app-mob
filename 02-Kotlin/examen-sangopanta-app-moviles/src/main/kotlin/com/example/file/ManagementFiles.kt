package com.example.file

import java.io.*

class ManagementFiles {
    fun readFile(path: String): List<List<String>> {
        try{
            val arr: ArrayList<String> = ArrayList()
            val reader = BufferedReader(FileReader(path))
            var linea = reader.readLine()
            while (linea !== null) {
                if (linea.isNotBlank()) {
                    arr.add(linea)
                }
                linea = reader.readLine()
            }
            val post = arr.map {it.split(",") }
            return post
        }catch (e:Exception){
            print(e.toString())
        }
        return emptyList()
    }


    fun writeFile(path: String, text: String, append: Boolean = true) {
        val file = File(path)
        val bw = BufferedWriter(FileWriter(file, append))
        bw.write(text)
        bw.close()
    }
}