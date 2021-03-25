package com.example.recipeeapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeeapp.R
import com.example.recipeeapp.data.model.MealsIngridients
import com.example.recipeeapp.data.model.MealsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_meal_categories.view.*

class SearchAdapter(val list:List<MealsIngridients>) : RecyclerView.Adapter<SearchAdapter.ItemViewHolder>(){

    var onItemClick:((list: MealsIngridients)->Unit)? = null

    inner class ItemViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        fun bind(user:MealsIngridients){
            itemView.apply {
                meal_name.text = user.strMeal
                Picasso.get().load(user.strMealThumb).into(meal_img)

                setOnClickListener {
                    onItemClick?.invoke(user)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_meal_categories,parent,false)
        )
    }

    override fun getItemCount(): Int{
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

}