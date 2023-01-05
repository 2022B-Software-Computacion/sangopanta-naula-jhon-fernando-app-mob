package com.example


import com.example.controller.PostsController
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.server.application.*


fun main() {
    /*embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
         .start(wait = true)*/
    val pruebas :  PostsController = PostsController()
    print(pruebas.read())

}


fun Application.module() {
    configureSerialization()
    configureRouting()
}
