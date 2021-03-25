package com.example.recipeeapp.data.api

import com.example.recipeeapp.data.model.CategoriesResponse
import com.example.recipeeapp.data.model.FilterCategoryResponse
import com.example.recipeeapp.data.model.FoodRecipieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserInterface {

    @GET("categories.php")
    suspend fun getMealCategories() : Response<CategoriesResponse>

    @GET("filter.php")
    suspend fun getFilteredMealCategories(@Query("c")c:String) : Response<FilterCategoryResponse>

    @GET("lookup.php")
    suspend fun getFoodRecipie(@Query("i")i:String) : Response<FoodRecipieResponse>

    @GET("search.php")
    suspend fun getSearchResponse(@Query("s")s:String) : Response<FoodRecipieResponse>

}