package com.lection.lection_03

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton

    private val itemList = mutableListOf(1, 2, 3, 4, 5)
    private val adapter = MyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        fab = findViewById(R.id.fab)

        val columns = if (resources.configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT) 3 else 4
        recyclerView.layoutManager = GridLayoutManager(this, columns)
        recyclerView.adapter = adapter

        adapter.setItems(itemList)

        fab.setOnClickListener {
            val newItem = itemList.size + 1
            itemList.add(newItem)
            adapter.addItem(newItem)
//            Log.d("ADD", "Item $newItem added")
        }


        savedInstanceState?.getIntegerArrayList("items")?.let {
            itemList.clear()
            itemList.addAll(it)
            adapter.setItems(itemList)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList("items", ArrayList(itemList))
    }
}
