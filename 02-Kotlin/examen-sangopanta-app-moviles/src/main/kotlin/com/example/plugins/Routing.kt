package com.example.plugins


import com.example.crud.PostsCRUD
import com.example.crud.UserCRUD
import com.example.entidades.Post
import com.example.entidades.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*


fun Application.configureRouting() {


    val post = PostsCRUD()
    val user = UserCRUD()

    install(io.ktor.server.plugins.cors.routing.CORS){
        anyHost()
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Patch)
        allowMethod(HttpMethod.Post)
        allowHeader(HttpHeaders.ContentType)
    }

    routing {
        get("/listarPosts") {
            call.respond(post.read())
        }


        get("/getByUserId/{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Post con id no encontrado",
                status = HttpStatusCode.NotFound
            )
            val post = post.getByUserId(id.toInt())
            call.respond(post)
        }

        get("/listarUsers"){
            call.respond(user.read())
        }

        // Delete user by ID
        delete("/deleteUser/{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Usuario no encontrado",
                status = HttpStatusCode.NotFound
            )
            user.delete(id.toInt(),"users.txt")
            call.respondText("Usuario eliminado", status = HttpStatusCode.Accepted)
        }

        /* Delete post by ID */
        delete("/deletePost/{id?}"){
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Post no encontrado",
                status = HttpStatusCode.NotFound
            )
            post.delete(id.toInt(),"posts.txt")
        }

        /*Create user */

        post("/saveUser"){
            val userNew = call.receive<User>()
            val isCreated = user.create(userNew,"users.txt")
            print(isCreated)
            if(isCreated){
                call.respondText("Usuario guardado correctamente",status  = HttpStatusCode.OK)
            }else{
                call.respondText("Usuario no ha sido creado", status = HttpStatusCode.Conflict)
            }
        }

        /* Create Post */
        post("/savePost"){
            val newPost = call.receive<Post>()
            val isCreated  = post.create(newPost,"posts.txt")
            if(isCreated){
                call.respondText("Post creado exitosamente", status = HttpStatusCode.OK)
            }else{
                call.respondText("Post no ha sido creado", status = HttpStatusCode.Conflict)
            }
        }

        /* Update Post */
        put("/updatePost/{id}"){
            val updatePost = call.receive<Post>()
            val postId = call.parameters["id"]?.toInt() ?: return@put call.respondText(
                "Post not found",
                status = HttpStatusCode.NotFound
            )

            val updated = post.update(updatePost,postId)
            if (updated){
                call.respondText("Updated successfully", status = HttpStatusCode.OK)
            }else{
                call.respondText("Could not update", status = HttpStatusCode.NotFound)
            }
        }

        /* Update a User */
        put("/updateUser/{id}"){
            val updateUser = call.receive<User>()
            val userId = call.parameters["id"]?.toInt() ?: return@put call.respondText(
                "User with this id not found",
                status = HttpStatusCode.NotFound
            )
            val updated = user.update(updateUser,userId)
            if(updated){
                call.respondText("Updated successfully", status = HttpStatusCode.OK)
            }else{
                call.respondText("Could not update", status = HttpStatusCode.OK)            }
        }
    }
}
