package com.example.macdonalds_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.macdonalds_app.R
import com.example.macdonalds_app.entities.Hamburguer
import com.example.macdonalds_app.providers.HambuguerProvider


class ProductAdapter(private val hamburguerList:List<Hamburguer>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {



    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemName: TextView
        var itemPrice: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemName = itemView.findViewById(R.id.item_name)
            itemPrice = itemView.findViewById(R.id.item_price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = hamburguerList[position].name
        holder.itemPrice.text = hamburguerList[position].price.toString()
        holder.itemImage.setImageResource(hamburguerList[position].image)

    }

    override fun getItemCount(): Int {
        return hamburguerList.size
    }


}