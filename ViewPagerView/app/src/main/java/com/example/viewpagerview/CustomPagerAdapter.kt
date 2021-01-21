package com.example.viewpagerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomPagerAdapter : RecyclerView.Adapter<ViewHolder>() {

    var views = listOf<View>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_a, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = views[position]
        holder.setView(view)
    }

    override fun getItemCount(): Int {
        return views.size
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textView: TextView = itemView.findViewById<TextView>(R.id.ViewA)
    fun setView(view: View) {
        textView.text = "뷰"
    }
}

