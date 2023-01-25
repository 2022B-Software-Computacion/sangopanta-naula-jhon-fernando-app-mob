package com.example.controlador

import com.example.entidades.Post
import com.example.file.ManagementFiles


class PostsController : AbstractController<Post, Int> {

    private val files = ManagementFiles()

    /* Delete a Post */
    override fun delete(id: Int, path: String) {
        val toEliminated = this.getById(id)
        val allPosts = this.read()
        val newPosts = allPosts.filter { it != toEliminated }
        val str = this.arrayToString(newPosts)
        this.files.writeFile(path, str)
    }

    /* Create a new Post */
    override fun create(Entity: Post, path: String): Boolean {
        val flag : Boolean
        val act = Entity.idPost
        if (verifyId(act)) {
            flag = false
        } else {
            val str = Entity.toString()
            files.writeFile(path, str)
            flag = true
        }
        return flag
    }

    /* Updates a Post */
    override fun update(Entity: Post, id: Int):Boolean {
        val toUpdate:Post? = this.getById(id)
        if (toUpdate !== null) {
            this.delete(id,"posts.txt")
            toUpdate.idUser = Entity.idUser
            toUpdate.idPost = Entity.idPost
            toUpdate.category = Entity.category
            toUpdate.title = Entity.title
            toUpdate.date= Entity.date
            toUpdate.description = Entity.description
            files.writeFile("posts.txt",toUpdate.toString())
            return true
        }
        return false

    }

    /* Return all posts that are found */
    override fun read(): List<Post> {
        val arr = files.readFile("posts.txt")
        val post = arr.map { Post(it[0].toInt(), it[1], it[2], it[3], it[4],it[5].toInt()) }
        return post
    }

    /* Returns a Post by its ID*/
    override fun getById(id: Int): Post? {
        val all = this.read()
        return all.find { it.idPost == id }
    }

    /*Returns posts from a User specific */
    fun getByUserId(id: Int): List<Post> {
        val all = this.read()
        return all.filter { it.idUser == id }
    }

    /*Verifies that exist only one post with the same id*/
    override fun verifyId(id: Int): Boolean {
        val all = this.read()
        val post = all.find { it.idPost == id }
        return post != null
    }

    /* Converts an array to string to save on txt file */
    override fun arrayToString(arr: List<Post>): String {
        var output: String = ""
        for (element in arr) {
            output = output + element.toString() + "\n"
        }
        return output.replace("\n\n\n", "\n")
    }

    /* Delete by User ID*/
    fun deleteByUserId(id: Int, path: String) {
        val all = this.read()
        val newPosts = all.filter {  it.idUser !== id }
        val str = this.arrayToString(newPosts)
        print(str)
        this.files.writeFile(path, str,false)
    }

}