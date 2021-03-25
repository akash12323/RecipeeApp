package com.example.recipeeapp.ui

import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.recipeeapp.R
import com.example.recipeeapp.data.api.Client
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_food_recipie.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class FoodRecipieActivity : AppCompatActivity() {

    lateinit var id:String
    lateinit var youtube:String
    lateinit var source:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_recipie)

        id = intent.getStringExtra("mealId")!!



        checkConnection()





        btn_youtube.setOnClickListener {
            val uri = Uri.parse(youtube)
            val i = Intent(Intent.ACTION_VIEW , uri)
            try{
                i.setPackage("com.google.android.youtube")
                startActivity(i)
            }
            catch (e:Exception){
                startActivity(i)
            }
        }
        btn_source.setOnClickListener {
            val uri = Uri.parse(source)
            val i = Intent(Intent.ACTION_VIEW , uri)
            startActivity(i)
        }

    }



    private fun checkConnection() {
        var connectivityManager = this.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo = connectivityManager.getActiveNetworkInfo()

        if (networkInfo != null && networkInfo.isConnected){
            getReciepe()
        }
        else if(networkInfo == null){
            AlertDialog.Builder(this)
                .setTitle("RETRY")
                .setMessage("NO INTERNET CONNECTION")
                .setPositiveButton("Retry",{ dialogInterface: DialogInterface, i: Int -> checkConnection() })
                .show()
        }
    }




    fun getReciepe(){
        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){ Client.api.getFoodRecipie(id) }

            if (response.isSuccessful){
                response.body()?.let {res->
                    res.meals?.let {
                        runOnUiThread {
                            tv_ingridients.text = ""
                            tv_quantity.text = ""

                            if (it[0].strIngredient1 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient1+"\n"
                            }
                            if (it[0].strIngredient2 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient2+"\n"
                            }
                            if (it[0].strIngredient3 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient3+"\n"
                            }
                            if (it[0].strIngredient4 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient4+"\n"
                            }
                            if (it[0].strIngredient5 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient5+"\n"
                            }
                            if (it[0].strIngredient6 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient6+"\n"
                            }
                            if (it[0].strIngredient7 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient7+"\n"
                            }
                            if (it[0].strIngredient8 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient8+"\n"
                            }
                            if (it[0].strIngredient9 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient9+"\n"
                            }
                            if (it[0].strIngredient10 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient10+"\n"
                            }
                            if (it[0].strIngredient11 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient11+"\n"
                            }
                            if (it[0].strIngredient12 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient12+"\n"
                            }
                            if (it[0].strIngredient13 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient13+"\n"
                            }
                            if (it[0].strIngredient14 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient14+"\n"
                            }
                            if (it[0].strIngredient15 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient15+"\n"
                            }
                            if (it[0].strIngredient16 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient16+"\n"
                            }
                            if (it[0].strIngredient17 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient17+"\n"
                            }
                            if (it[0].strIngredient18 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient18+"\n"
                            }
                            if (it[0].strIngredient19 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient19+"\n"
                            }
                            if (it[0].strIngredient20 != ""){
                                tv_ingridients.text = tv_ingridients.text as String + it[0].strIngredient20+"\n"
                            }



                            if (it[0].strMeasure1 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure1+"\n"
                            }
                            if (it[0].strMeasure2 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure2+"\n"
                            }
                            if (it[0].strMeasure3 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure3+"\n"
                            }
                            if (it[0].strMeasure4 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure4+"\n"
                            }
                            if (it[0].strMeasure5 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure5+"\n"
                            }
                            if (it[0].strMeasure6 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure6+"\n"
                            }
                            if (it[0].strMeasure7 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure7+"\n"
                            }
                            if (it[0].strMeasure8 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure8+"\n"
                            }
                            if (it[0].strMeasure9 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure9+"\n"
                            }
                            if (it[0].strMeasure10 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure10+"\n"
                            }
                            if (it[0].strMeasure11 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure11+"\n"
                            }
                            if (it[0].strMeasure12 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure12+"\n"
                            }
                            if (it[0].strMeasure13 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure13+"\n"
                            }
                            if (it[0].strMeasure14 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure14+"\n"
                            }
                            if (it[0].strMeasure15 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure15+"\n"
                            }
                            if (it[0].strMeasure16 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure16+"\n"
                            }
                            if (it[0].strMeasure17 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure17+"\n"
                            }
                            if (it[0].strMeasure18 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure18+"\n"
                            }
                            if (it[0].strMeasure19 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure19+"\n"
                            }
                            if (it[0].strMeasure20 != ""){
                                tv_quantity.text = tv_quantity.text as String + it[0].strMeasure20+"\n"
                            }

//                            tv_quantity.text = it[0].strMeasure1+"\n"+it[0].strMeasure2+"\n"+it[0].strMeasure3+"\n"+it[0].strMeasure4+"\n"+it[0].strMeasure5+"\n"+
//                                    it[0].strMeasure6+"\n"+it[0].strMeasure7+"\n"+it[0].strMeasure8+"\n"+it[0].strMeasure9+"\n"+it[0].strMeasure10+"\n"+
//                                    it[0].strMeasure11+"\n"+it[0].strMeasure12+"\n"+it[0].strMeasure13+"\n"+it[0].strMeasure14+"\n"+it[0].strMeasure15+"\n"+
//                                    it[0].strMeasure16+"\n"+it[0].strMeasure17

                            tv_area.text = it.get(0).strArea.toString()
                            tv_category.text = it.get(0).strCategory.toString()
                            tv_instructions.text = it.get(0).strInstructions.toString()
                            Picasso.get().load(it[0].strMealThumb).into(backdrop)
                            title_on_appbar.text = it[0].strMeal
                            youtube = it[0].strYoutube.toString()
                            source = it[0].strSource.toString()
                            ll.visibility = View.VISIBLE
                        }
                    }
                }
            }
            else if (response.code() == 404 || !response.isSuccessful){
                AlertDialog.Builder(this@FoodRecipieActivity)
                    .setTitle("RETRY")
                    .setMessage("Failed to load the response")
                    .setPositiveButton("Retry",{ dialogInterface: DialogInterface, i: Int -> getReciepe() })
                    .show()
            }
        }
    }
}