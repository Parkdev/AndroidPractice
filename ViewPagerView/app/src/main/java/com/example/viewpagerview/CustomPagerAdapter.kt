package com.example.viewpagerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomPagerAdapter : RecyclerView.Adapter<ViewHolder>() {

    var views = listOf<View>()


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = when (viewType) {
            0 -> LayoutInflater.from(parent.context).inflate(R.layout.layout_a, parent, false)
            1 -> LayoutInflater.from(parent.context).inflate(R.layout.layout_b, parent, false)
            2 -> LayoutInflater.from(parent.context).inflate(R.layout.layout_c, parent, false)
            3 -> LayoutInflater.from(parent.context).inflate(R.layout.layout_d, parent, false)
            else -> LayoutInflater.from(parent.context).inflate(R.layout.layout_d, parent, false)
        }

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = views[position]
    }

    override fun getItemCount(): Int {
        return views.size
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
}

