package com.example.examen_2do_parcial.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_2do_parcial.R
import com.example.examen_2do_parcial.activities.CrearPost
import com.example.examen_2do_parcial.activities.PostActivity
import com.example.examen_2do_parcial.crud.PostCRUD
import com.example.examen_2do_parcial.entities.Post

class PostAdapter(private val postList: List<Post>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val postCRUD = PostCRUD()
        val context: Context

        val textViewId: TextView
        val textViewTitle: TextView
        val textViewDescription: TextView
        val textViewDate: TextView
        val textViewCategory: TextView
        val buttonDelete: Button
        val buttonEdit: Button


        init {

            textViewId = itemView.findViewById(R.id.textViewId)
            textViewTitle = itemView.findViewById(R.id.textViewTitle)
            textViewDescription = itemView.findViewById(R.id.textViewDescription)
            textViewDate = itemView.findViewById(R.id.textViewDate)
            textViewCategory = itemView.findViewById(R.id.textViewCategory)
            buttonDelete = itemView.findViewById(R.id.btn_eliminar_post)
            buttonEdit = itemView.findViewById(R.id.btn_actualizar_post)
            context = itemView.context
            setOnClickListeners { onClick(it) }
        }
        fun setOnClickListeners(onClick: (View) -> Unit) {
            buttonDelete.setOnClickListener(onClick)
            buttonEdit.setOnClickListener(onClick)
        }
        fun onClick(v: View?) {
            when (v?.id) {
                R.id.btn_eliminar_post -> {
                    postCRUD.delete(textViewId.text.toString()){
                        val intent = Intent(context, PostActivity::class.java)
                        context.startActivity(intent)
                    }
                }

                R.id.btn_actualizar_post -> {
                    val intent = Intent(context, CrearPost::class.java)
                    intent.putExtra("title",textViewTitle.text)
                    intent.putExtra("description",textViewDescription.text)
                    intent.putExtra("date",textViewDate.text)
                    intent.putExtra("category",textViewCategory.text)
                    context.startActivity(intent)
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPost = postList[position]
        holder.textViewId.text = currentPost.idPost
        holder.textViewTitle.text = currentPost.title
        holder.textViewDescription.text = currentPost.description
        holder.textViewDate.text = currentPost.date
        holder.textViewCategory.text = currentPost.category
    }


    override fun getItemCount(): Int {
        return postList.size
    }

}
