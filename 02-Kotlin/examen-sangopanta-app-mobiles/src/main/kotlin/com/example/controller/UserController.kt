package com.example.controlador

import com.example.entidades.User
import com.example.file.ManagementFiles


class UserController : AbstractController<User, Int> {
    val files = ManagementFiles()
    val posts= PostsController()

    override fun delete(id: Int, path: String) {
        val toEliminated = this.getById(id)
        val allUsers = this.read()
        val newUsers = allUsers.filter { it -> it != toEliminated }
        val str = this.arrayToString(newUsers)
        this.files.writeFile(path, str, false)
    }

    override fun create(Entity: User, path: String): Boolean {
        var flag = false
        val act = Entity.idUser
        if (verifyId(act)) {
            flag = false
        } else {
            val str = Entity.toString()
            files.writeFile(path, str)
            flag = true
        }
        return flag
    }

    override fun update(Entity: User) {
        TODO("Not yet implemented")
    }

    override fun read(): List<User> {
        val users: ArrayList<User> = ArrayList()
        val arr = files.readFile("users.txt")
        // colocar los datos en el array de usuarios
        for (element in arr) {
            val user = User(element[0].toInt(), element[1], element[2], null)
            user.posts = posts.getByUserId(user.idUser)
            users.add(user)
        }

        return users
    }

    override fun verifyId(id: Int): Boolean {
        val all = this.read()
        val user = all.find { it -> it.idUser == id }
        return user != null
    }

    override fun getById(id: Int): User? {
        val all = this.read()
        val user = all.find { it.idUser == id }
        return user
    }

    override fun arrayToString(arr: List<User>): String {
        var output: String = ""
        for (element in arr) {
            output = output + element.toString() + "\n"
        }
        return output.replace("\n\n\n", "\n")
    }
}