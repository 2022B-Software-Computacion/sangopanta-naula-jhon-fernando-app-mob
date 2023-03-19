package com.example.examen_2do_parcial.crud

import com.example.examen_2do_parcial.entities.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserCRUD: AbstractCRUD<User> {
    private val db = Firebase.firestore

    override fun create(obj: User, onSuccess: (String) -> Unit) {
        db.collection("users")
            .add(obj)
            .addOnSuccessListener { documentReference ->
                val id = documentReference.id
                db.collection("users").document(id)
                    .update("idUser", id)
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



    override fun read( onSucces: (List<User>) -> Unit) {
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                val list = mutableListOf<User>()
                for (document in result) {
                    if (document !== null){
                        val user = document.toObject(User::class.java)
                        list.add(user)
                    }
                }
                onSucces(list)
            }
            .addOnFailureListener {
                onSucces(listOf())
            }
    }


    override fun update(obj: User, onSuccess: (String) -> Unit) {
        if (obj.idUser == null) {
            throw IllegalArgumentException("User id is null")
        }

        val docRef = db.collection("users").document(obj.idUser!!)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    docRef.update("name", obj.name, "lastName", obj.lastName, "age", obj.age, "email", obj.email)
                        .addOnSuccessListener {
                            onSuccess("User updated")
                        }
                        .addOnFailureListener { exception ->
                            onSuccess(exception.message.toString())
                        }
                } else {
                    onSuccess("User not found")
                }
            }
            .addOnFailureListener { exception ->
                onSuccess(exception.message.toString())
            }
    }



    override fun delete(id: String, onSuccess: (String) -> Unit) {
        // Eliminamos todas las colecciones del usuario
        db.collection("users").document(id).collection("posts")
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    document.reference.delete()
                }
                // Eliminamos al usuario
                db.collection("users").document(id)
                    .delete()
                    .addOnSuccessListener {
                        onSuccess("User and associated collections deleted")
                    }
                    .addOnFailureListener { exception ->
                        onSuccess(exception.message.toString())
                    }
            }
            .addOnFailureListener { exception ->
                onSuccess(exception.message.toString())
            }
    }


    override fun getById(id: String, onSuccess: (User) -> Unit) {
        db.collection("users").document(id)
            .get()
            .addOnSuccessListener { document ->
                val user = document.toObject(User::class.java)
                onSuccess(user as User)
            }
            .addOnFailureListener { exception ->
                onSuccess(User("", "", "", 0, ""))
            }
    }


}