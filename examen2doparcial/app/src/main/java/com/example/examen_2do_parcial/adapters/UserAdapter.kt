package com.example.examen_2do_parcial.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_2do_parcial.R
import com.example.examen_2do_parcial.activities.ActualizarInformacion
import com.example.examen_2do_parcial.activities.PostActivity
import com.example.examen_2do_parcial.activities.UserActivity
import com.example.examen_2do_parcial.crud.UserCRUD
import com.example.examen_2do_parcial.entities.User

class UserAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val userCrud = UserCRUD()

        val context: Context

        val textViewName: TextView
        val textViewId: TextView
        val textViewAge: TextView
        val textViewEmail: TextView
        val buttonDelete: Button
        val buttonEdit: Button
        val buttonPost: Button

        init {
            textViewId = itemView.findViewById(R.id.textViewId)
            textViewName = itemView.findViewById(R.id.textViewName)
            textViewAge = itemView.findViewById(R.id.textViewAge)
            textViewEmail = itemView.findViewById(R.id.textViewEmail)
            buttonDelete = itemView.findViewById(R.id.btn_eliminar_usuario)
            buttonEdit = itemView.findViewById(R.id.btn_actualizar_usuario)
            buttonPost = itemView.findViewById(R.id.btn_posts)
            context = itemView.context
            setOnClickListeners { onClick(it) }
        }

        fun setOnClickListeners(onClick: (View) -> Unit) {
            buttonPost.setOnClickListener(onClick)
            buttonDelete.setOnClickListener(onClick)
            buttonEdit.setOnClickListener(onClick)
        }

        fun onClick(v: View?) {
            when (v?.id) {
                R.id.btn_posts -> {
                    val intent = Intent(context, PostActivity::class.java)
                    intent.putExtra("id",textViewId.text)
                    context.startActivity(intent)
                }
                R.id.btn_actualizar_usuario -> {
                    val intent = Intent(context, ActualizarInformacion::class.java)
                    intent.putExtra("id",textViewId.text)
                    intent.putExtra("name", textViewName.text)
                    intent.putExtra("lastName", textViewName.text)
                    intent.putExtra("age", textViewAge.text)
                    intent.putExtra("email", textViewEmail.text)
                    context.startActivity(intent)
                }
                R.id.btn_eliminar_usuario -> {
                    userCrud.delete(textViewId.text.toString()){
                        val intent = Intent(context, UserActivity::class.java)
                        context.startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.textViewId.text = currentItem.idUser
        holder.textViewName.text = currentItem.name + " " + currentItem.lastName
        holder.textViewAge.text = currentItem.age.toString()
        holder.textViewEmail.text = currentItem.email
    }
    override fun getItemCount() = userList.size

}





