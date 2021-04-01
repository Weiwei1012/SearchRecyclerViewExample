package com.example.searchrecyclerviewexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    lateinit var adapter: RecyclerView_Adapter
    lateinit var details_rv: RecyclerView
    lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //detail_text.text = getIntent().getStringExtra("passsearched")
        details_rv = findViewById(R.id.details_rv)
        gridLayoutManager = GridLayoutManager(
            details_rv.context, 1,
            LinearLayoutManager.VERTICAL, false
        )
        details_rv.layoutManager = gridLayoutManager
        details_rv.setHasFixedSize(true)

        getList()
    }

    private fun getList() {
        val results_items = arrayOf(getIntent().getStringExtra("passsearched")!!)
        val resultList = ArrayList<String>()
        for (i in results_items) {
            resultList.add(i)
        }
        adapter = RecyclerView_Adapter(resultList)
        details_rv.adapter = adapter
    }
}
