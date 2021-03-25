package com.example.recipeeapp.ui

import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeeapp.R
import com.example.recipeeapp.data.api.Client
import com.example.recipeeapp.data.model.MealsItem
import com.example.recipeeapp.ui.adapter.FilterCategoryAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_food.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FoodActivity : AppCompatActivity() {

    val list = arrayListOf<MealsItem>()
    val filterCategoryAdapter = FilterCategoryAdapter(list)

    lateinit var mealName:String
    lateinit var mealImg:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        mealName = intent.getStringExtra("MealCategoryName")!!
        mealImg = intent.getStringExtra("MealCategoryImage")!!
        val mealDesc = intent.getStringExtra("MealCategoryDesc")!!





        checkConnection()




        backdrop.setOnClickListener {
            AlertDialog.Builder(this)
                .setPositiveButton("CLOSE",{ dialogInterface: DialogInterface, i: Int -> } )
                .setTitle(mealName.toUpperCase())
                .setMessage(mealDesc)
                .show()
        }

        filterCategoryAdapter.onItemClick = {
            val i = Intent(this,FoodRecipieActivity::class.java)
            i.putExtra("mealId",it.idMeal)
            startActivity(i)
        }

//        getFilteredCategory()

        food_rview.apply {
            layoutManager = GridLayoutManager(this@FoodActivity,3,RecyclerView.VERTICAL,false)
            adapter = filterCategoryAdapter
        }
    }

    private fun checkConnection() {
        var connectivityManager = this.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo = connectivityManager.getActiveNetworkInfo()

        if (networkInfo != null && networkInfo.isConnected){
            getFilteredCategory()
        }
        else if(networkInfo == null){
            AlertDialog.Builder(this)
                .setTitle("RETRY")
                .setMessage("NO INTERNET CONNECTION")
                .setPositiveButton("Retry",{ dialogInterface: DialogInterface, i: Int -> checkConnection() })
                .show()
        }
    }

    private fun getFilteredCategory() {
        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){ Client.api.getFilteredMealCategories("${mealName}") }

            if (response.isSuccessful){
                response.body()?.let { res->
                    res.meals?.let {
                        list.addAll(it)
                    }
                    runOnUiThread {
                        filterCategoryAdapter.notifyDataSetChanged()
                        pBar.visibility = View.GONE
                        appbar.visibility = View.VISIBLE
                        food_rview.visibility = View.VISIBLE
                        title_on_appbar.text = mealName
                        Picasso.get().load(mealImg).into(backdrop)
                    }
                }
            }
            else if (response.code() != 202 || !response.isSuccessful){
                AlertDialog.Builder(this@FoodActivity)
                    .setTitle("RETRY")
                    .setMessage("Failed to load the response")
                    .setPositiveButton("Retry",{ dialogInterface: DialogInterface, i: Int -> getFilteredCategory() })
                    .show()
            }
        }
    }
}