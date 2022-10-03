package com.example.firebaseshoppingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseshoppingapp.R
import com.example.firebaseshoppingapp.model.Category
import com.squareup.picasso.Picasso

class CategoryAdapter(private val itemList : ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.category_item_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(itemList[position].image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    class ViewHolder(private val itemView : View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.imageView)

    }
}