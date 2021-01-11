package com.example.grocerydelivery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerydelivery.data.StoreItem

class StoresAdapter(private val storeItems: List<StoreItem>) :
    RecyclerView.Adapter<StoresAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.a_single_store_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = storeItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val storeItem = storeItems[position]
        holder.bind(storeItem)

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(storeItem: StoreItem) {
            itemView.findViewById<TextView>(R.id.storeItemTitle).text = storeItem.name
        }

    }
}