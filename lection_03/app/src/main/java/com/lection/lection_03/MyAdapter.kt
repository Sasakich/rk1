package com.lection.lection_03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {

    private val items = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
        var colorResId = if (position % 2 == 0) R.color.red else R.color.blue
        holder.itemView.setBackgroundColor(holder.itemView.context.getColor(colorResId))

        holder.itemView.setOnClickListener {
            colorResId = if (colorResId == R.color.red) R.color.blue else R.color.red
            holder.itemView.setBackgroundColor(holder.itemView.context.getColor(colorResId))
        }
    }

    fun setItems(newItems: List<Int>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addItem(item: Int) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }
}
