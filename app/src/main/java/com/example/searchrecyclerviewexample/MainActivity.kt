package com.example.searchrecyclerviewexample

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    lateinit var adapter: RecyclerView_Adapter
    lateinit var ingredients_rv: RecyclerView
    lateinit var gridLayoutManager: GridLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        
        ingredients_rv = findViewById(R.id.ingredients_rv)
        gridLayoutManager = GridLayoutManager(
            ingredients_rv.context, 2,
            LinearLayoutManager.VERTICAL, false
        )
        ingredients_rv.layoutManager = gridLayoutManager
        ingredients_rv.setHasFixedSize(true)

        val all_ingredients: Array<String> = resources.getStringArray(R.array.ingredients)

        ingredients_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (all_ingredients.contains(query)){
                    val intent = Intent(baseContext, DetailsActivity::class.java)
                    intent.putExtra("passsearched", query)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(applicationContext,"無此食材料理",Toast.LENGTH_LONG).show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
        getList()
    }

    private fun getList() {
        val hotlist_items = arrayOf("快速晚餐","高麗菜","馬鈴薯","簡易家常菜","雞肉","超簡單甜點","家常菜 肉","減醣")
        val hotList = ArrayList<String>()
        for (i in hotlist_items) {
            hotList.add(i)
        }
        adapter = RecyclerView_Adapter(hotList)
        ingredients_rv.adapter = adapter
    }


}
