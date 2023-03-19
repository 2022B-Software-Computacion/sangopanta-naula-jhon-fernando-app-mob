package com.example.examen_2do_parcial.crud

import com.example.examen_2do_parcial.entities.Post
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PostCRUD : AbstractCRUD<Post> {
    private val db = Firebase.firestore

    /**
     * Create a new post
     * @param obj Post
     * @param onSuccess String
     */
    override fun create(obj: Post, onSuccess: (String) -> Unit) {
        db.collection("users").document(obj.idUser)
            .collection("posts")
            .add(obj)
            .addOnSuccessListener { documentReference ->
                val id = documentReference.id
                db.collection("users").document(obj.idUser)
                    .collection("posts").document(id)
                    .update("idPost", id)
                    .addOnSuccessListener {
                        onSuccess(id)
                    }
                    .addOnFailureListener { exception ->
                        onSuccess(exception.message.toString())
                    }
            }
            .addOnFailureListener { exception ->
                onSuccess(exception.message.toString())
            }
    }

    /**
     * Create Post By User Id
     * @param idUser String
     * @param onSucces Post
     */
    fun createByUserId(idUser: String, obj: Post, onSucces: (Post) -> Unit) {
        db.collection("users").document(idUser)
            .collection("posts")
            .add(obj)
            .addOnSuccessListener { documentReference ->
                val id = documentReference.id
                db.collection("users").document(idUser)
                    .collection("posts").document(id)
                    .update("idPost", id)
                    .addOnSuccessListener {
                        obj.idPost = id
                        onSucces(obj)
                    }
                    .addOnFailureListener { exception ->
                        onSucces(obj)
                    }
            }
            .addOnFailureListener { exception ->
                onSucces(obj)
            }
    }



    /**
     * Read all posts
     * @param onSucces List<Post>
     */

    override  fun read(onSucces: (List<Post>) -> Unit) {
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                val list = mutableListOf<Post>()
                for (document in result) {
                    if (document !== null) {
                        val post = document.toObject(Post::class.java)
                        list.add(post)
                    }

                }
                onSucces(list)
            }
            .addOnFailureListener {
                onSucces(listOf())
            }
    }

    /**
     * Read all posts by user id
     * @param id String user id
     * @param onSucces List<Post>
     */

    fun readByUserId(id: String, onSucces: (List<Post>) -> Unit) {
        db.collection("users").document(id)
            .collection("posts")
            .get()
            .addOnSuccessListener { result ->
                val list = mutableListOf<Post>()
                for (document in result) {
                    if (document !== null) {
                        val post = document.toObject(Post::class.java)
                        list.add(post)
                    }

                }
                onSucces(list)
            }
            .addOnFailureListener {
                onSucces(listOf())
            }
    }
    /**
     * Update a post
     * @param obj Post
     * @param onSuccess String
     */

    override fun update(obj: Post, onSuccess: (String) -> Unit) {
        db.collection("users").document(obj.idUser)
            .collection("posts").document(obj.idPost)
            .set(obj)
            .addOnSuccessListener {
                onSuccess("Post actualizado")
            }
            .addOnFailureListener { exception ->
                onSuccess(exception.message.toString())
            }
    }

    /**
     * Delete a post
     * @param id String
     * @param onSuccess String
     */
    override fun delete(id: String, onSuccess: (String) -> Unit) {
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document != null) {
                        val userId = document.id
                        db.collection("users").document(userId)
                            .collection("posts")
                            .document(id)
                            .delete()
                            .addOnSuccessListener {
                                onSuccess("Post eliminado")
                                return@addOnSuccessListener
                            }
                            .addOnFailureListener { exception ->
                                onSuccess(exception.message.toString())
                                return@addOnFailureListener
                            }
                    }
                }
                onSuccess("No se encontró el post con el id $id")
            }
            .addOnFailureListener { exception ->
                onSuccess(exception.message.toString())
            }
    }





    /**
     * Get a post by id
     * @param id String
     * @param onSuccess Post
     */

    override fun getById(id: String, onSuccess: (Post) -> Unit) {
        db.collection("users").document(id)
            .collection("posts").document(id)
            .get()
            .addOnSuccessListener { result ->
                val post = result.toObject(Post::class.java)
                onSuccess(post!!)
            }
            .addOnFailureListener { exception ->
                onSuccess(exception.message.toString() as Post)
            }
    }
}