package com.example.recipeeapp.data.model

import com.google.gson.annotations.SerializedName

data class FilterCategoryResponse(

    @field:SerializedName("meals")
    val meals: List<MealsItem>? = null
)

data class MealsItem(

    @field:SerializedName("strMealThumb")
    val strMealThumb: String? = null,

    @field:SerializedName("idMeal")
    val idMeal: String? = null,

    @field:SerializedName("strMeal")
    val strMeal: String? = null
)
