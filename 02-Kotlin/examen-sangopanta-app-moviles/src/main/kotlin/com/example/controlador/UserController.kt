package com.example.controlador

import com.example.entidades.User
import com.example.file.ManagementFiles


class UserController : AbstractController<User, Int> {
    private val files = ManagementFiles()
    private val posts= PostsController()


    /* Delete a specific user */
    override fun delete(id: Int, path: String) {
        val toEliminated = this.getById(id)
        val allUsers = read()

        allUsers.map {
            if(id == it.idUser){
                posts.deleteByUserId(id,"posts.txt")
            }
        }
        val newUsers = allUsers.filter {
            it != toEliminated
            }
        val str = this.arrayToString(newUsers)
        this.files.writeFile(path, str,false)
    }

    /* Create a new user */

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

    /* Update a  user */
    override fun update(Entity: User, id:Int) :Boolean{
        val toUpdate:User ? = this.getById(id)
        if(toUpdate !== null){
            this.delete(id,"users.txt")
            toUpdate.idUser = id
            toUpdate.email = Entity.email
            toUpdate.age = Entity.age
            toUpdate.name = Entity.name
            toUpdate.lastName = Entity.lastName
            toUpdate.posts = toUpdate.posts
            files.writeFile("users.txt",toUpdate.toString())
            return true
        }
        return false
    }

    override fun read(): List<User> {
        val users: ArrayList<User> = ArrayList()
        val arr = files.readFile("users.txt")
        // colocar los datos en el array de usuarios
        for (element in arr) {
            val user = User(element[0].toInt(), element[1], element[2],element[3].toInt(),element[4],null)
            user.posts = posts.getByUserId(user.idUser)

            users.add(user)
        }

        return users
    }

    override fun verifyId(id: Int): Boolean {
        val all = this.read()
        val user = all.find { it.idUser == id }
        return user != null
    }

    override fun getById(id: Int): User? {
        val all = this.read()
        return all.find { it.idUser == id }
    }

    override fun arrayToString(arr: List<User>): String {
        var output: String = ""
        for (element in arr) {
            output = output + element.toString() + "\n"
        }
        return output.replace("\n\n\n", "\n")
    }
}