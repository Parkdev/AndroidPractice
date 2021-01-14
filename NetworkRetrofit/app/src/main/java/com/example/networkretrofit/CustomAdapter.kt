package com.example.networkretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.networkretrofit.databinding.ItemRecyclerBinding

class CustomAdapter : RecyclerView.Adapter<Holder>() {

    var userList = mutableListOf<RepositoryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = userList[position]
        holder.setUser(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}

class Holder(private val binding:ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setUser(user: RepositoryItem) {
        binding.textName.text = user.name
        binding.textId.text = user.node_id
        Glide.with(itemView).load(user.owner.avatar_url).into(binding.imageAvater)
    }
}





