package com.example.recipeeapp.ui

import android.app.SearchManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeeapp.R
import com.example.recipeeapp.data.api.Client
import com.example.recipeeapp.data.model.CategoriesItem
import com.example.recipeeapp.data.model.MealsIngridients
import com.example.recipeeapp.ui.adapter.CategoriesAdapter
import com.example.recipeeapp.ui.adapter.SearchAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val list = arrayListOf<CategoriesItem>()
    val categoriesAdapter = CategoriesAdapter(list)


    val searchList = arrayListOf<MealsIngridients>()
    val searchAdapter = SearchAdapter(searchList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)


        categoriesAdapter.onItemClick = {
            var i = Intent(this,FoodActivity::class.java)

            i.putExtra("MealCategoryName",it.strCategory)
            i.putExtra("MealCategoryImage",it.strCategoryThumb)
            i.putExtra("MealCategoryDesc",it.strCategoryDescription)

            startActivity(i)
        }


        checkConnection()

    }


    private fun checkConnection() {
        var connectivityManager = this.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo = connectivityManager.getActiveNetworkInfo()

        if (networkInfo != null && networkInfo.isConnected){
            getMeals()
        }
        else if(networkInfo == null){
            AlertDialog.Builder(this)
                .setTitle("RETRY")
                .setMessage("NO INTERNET CONNECTION")
                .setPositiveButton("Retry",{ dialogInterface: DialogInterface, i: Int -> checkConnection() })
                .show()
        }
    }





    private fun getMeals() {
        meal_categories.apply {
            layoutManager = GridLayoutManager(this@MainActivity,3,RecyclerView.VERTICAL,false)
            adapter = categoriesAdapter
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){ Client.api.getMealCategories() }

            if (response.isSuccessful){
                response.body()?.let { res->
                    res.categories?.let {
                        list.clear()
                        list.addAll(it)
                    }
                    runOnUiThread {
                        categoriesAdapter.notifyDataSetChanged()
//                        mainLayout.visibility = View.VISIBLE
                    }
                }
            }
            else if (response.code() != 202 || !response.isSuccessful){
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("RETRY")
                    .setMessage("Failed to load the response")
                    .setPositiveButton("Retry",{ dialogInterface: DialogInterface, i: Int -> getMeals() })
                    .show()
            }
        }
    }


    private fun searchMeals(text : String){
        if (text.length > 2){
            meal_categories.apply {
                layoutManager = GridLayoutManager(this@MainActivity,2,RecyclerView.VERTICAL,false)
                adapter = searchAdapter
            }

            searchAdapter.onItemClick = {
                val i = Intent(this,FoodRecipieActivity::class.java)
                i.putExtra("mealId",it.idMeal.toString())
                startActivity(i)
            }

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO){ Client.api.getSearchResponse(text) }

                if (response.isSuccessful){
                    response.body()?.let {res->
                        res.meals?.let {
                            searchList.clear()
                            searchList.addAll(it)
                            runOnUiThread {
                                searchAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        }
        else{
            getMeals()
        }
    }






    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

        val item = menu?.findItem(R.id.search)
        val searchView = item?.actionView as SearchView

        searchView.setQueryHint("TYPE HERE TO SEARCH")

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!!.length > 2){
                    searchMeals(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.length>30){
                    searchMeals(newText.toString())
                }
                if (newText.length<=0){
                    getMeals()
                }
                return false
            }

        })

        item.icon.setVisible(false,false)

        return true
    }
}