package com.example.plugins

import com.example.controller.PostsController
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {


    val post: PostsController = PostsController()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/listarPosts") {
            call.respond(post.read())
        }

        get("/getById/{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Post con id no encontrado",
                status = HttpStatusCode.NotFound
            )
            val post = post.getByUserId(id.toInt())
            call.respond(post)
        }


    }
}
