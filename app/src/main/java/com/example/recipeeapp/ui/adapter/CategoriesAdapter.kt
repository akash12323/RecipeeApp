package com.example.recipeeapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeeapp.R
import com.example.recipeeapp.data.model.CategoriesItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_meal_categories.view.*

class CategoriesAdapter(val list:List<CategoriesItem>) : RecyclerView.Adapter<CategoriesAdapter.ItemViewHolder>() {

    var onItemClick:((list:CategoriesItem)->Unit)? = null

    inner class ItemViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        fun bind(user:CategoriesItem){
            itemView.apply {
                meal_name.text = user.strCategory.toString()
                Picasso.get().load(user.strCategoryThumb).into(meal_img)

                setOnClickListener {
                    Toast.makeText(context,"${user.strCategory} ${adapterPosition}",Toast.LENGTH_SHORT).show()
                    onItemClick?.invoke(user)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val i = LayoutInflater.from(parent.context).inflate(R.layout.layout_meal_categories,parent,false)

        return ItemViewHolder(i)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }
}