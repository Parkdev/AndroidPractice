package com.example.grocerydelivery

import StoresAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerydelivery.data.StoreItem
import androidx.recyclerview.widget.LinearLayoutManager as LinearLayoutManager

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }

        val storeItems = listOf(
            StoreItem("Ubuntu", 1.99, "Awesome"),
            StoreItem("Ubuntu2", 2.99, "Awesome", true),
            StoreItem("Ubuntu3", 3.99, "Awesome"),
            StoreItem("Ubuntu4", 4.99, "Awesome", true),
            StoreItem("Ubuntu5", 5.99, "Awesome"),
            StoreItem("Ubuntu6", 6.99, "Awesome")
        )


        view.findViewById<RecyclerView>(R.id.storesRecyclerView).apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = StoresAdapter(storeItems)
        }

    }

}