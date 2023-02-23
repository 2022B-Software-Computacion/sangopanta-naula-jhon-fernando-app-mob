package com.example.macdonalds_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.macdonalds_app.R
import com.example.macdonalds_app.entities.Hamburguer

class ItemCartAdapter(private val hamburguerList:List<Hamburguer>) : RecyclerView.Adapter<ItemCartAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemName: TextView
        var itemPrice: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image_view)
            itemName = itemView.findViewById(R.id.item_name_text_view)
            itemPrice = itemView.findViewById(R.id.item_price_text_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return hamburguerList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = hamburguerList[position].name
        holder.itemPrice.text = hamburguerList[position].price.toString()
        holder.itemImage.setImageResource(hamburguerList[position].image)
    }
}