package com.example.controlador

import com.example.entidades.Post
import com.example.file.ManagementFiles


class PostsController : AbstractController<Post, Int> {

    val files = ManagementFiles()

    override fun delete(id: Int, path: String) {
        val toEliminated = this.getById(id)
        val allPosts = this.read()
        val newPosts = allPosts.filter { it -> it != toEliminated }
        val str = this.arrayToString(newPosts)
        this.files.writeFile(path, str, false)

    }

    override fun create(Entity: Post, path: String): Boolean {
        var flag = false
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

    override fun update(Entity: Post) {
        TODO("Not yet implemented")
    }

    // Return all posts that are found
    override fun read(): List<Post> {
        val arr = files.readFile("posts.txt")
        val post = arr.map { it -> Post(it[0].toInt(), it[1], it[2], it[3].toInt()) }
        return post
    }

    // Returns a Post by its ID
    override fun getById(id: Int): Post? {
        val all = this.read()
        return all.find { it -> it.idPost == id }
    }

    // Returns posts from a User specific
    fun getByUserId(id: Int): List<Post> {
        val all = this.read()
        val postFromUsers = all.filter { it -> it.idUser == id }
        return postFromUsers
    }

    //Verify that exist only one post with the same id
    override fun verifyId(id: Int): Boolean {
        val all = this.read()
        val post = all.find { it -> it.idPost == id }
        return post != null
    }

    override fun arrayToString(arr: List<Post>): String {
        var output: String = ""
        for (element in arr) {
            output = output + element.toString() + "\n"
        }
        return output.replace("\n\n\n", "\n")
    }


}