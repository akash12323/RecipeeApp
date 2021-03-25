package com.example.recipeeapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeeapp.R
import com.example.recipeeapp.data.model.CategoriesItem
import com.example.recipeeapp.data.model.MealsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_meal_categories.view.*

class FilterCategoryAdapter(val list : List<MealsItem>) : RecyclerView.Adapter<FilterCategoryAdapter.ItemViewHolder>() {

    var onItemClick:((list: MealsItem)->Unit)? = null

    inner class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(user : MealsItem){
            itemView.apply {
                meal_name.text = user.strMeal.toString()
                Picasso.get().load(user.strMealThumb).into(meal_img)

                setOnClickListener {
                    onItemClick?.invoke(user)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.filter_category,parent,false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }
}