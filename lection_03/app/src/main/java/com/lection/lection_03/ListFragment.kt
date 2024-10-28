package com.lection.lection_03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private val adapter = MyAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        fab = view.findViewById(R.id.fab)

        val columns = if (resources.configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT) 3 else 4
        recyclerView.layoutManager = GridLayoutManager(requireContext(), columns)
        recyclerView.adapter = adapter

        savedInstanceState?.getIntegerArrayList("items")?.let {
            viewModel.setItems(it)
        }

        fab.setOnClickListener {
            viewModel.addItem()
        }

        viewModel.items.observe(viewLifecycleOwner) { items ->
            adapter.setItems(items)
        }

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val currentItems = ArrayList(viewModel.items.value ?: mutableListOf())
        outState.putIntegerArrayList("items", currentItems)
    }
}
