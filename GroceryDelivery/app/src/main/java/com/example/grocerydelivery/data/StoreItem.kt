package com.example.grocerydelivery.data

import com.example.grocerydelivery.R

data class StoreItem(
    val productCode: String,
    val name: String,
    val price: Double,
    val description: String,
    val isHighlyRated: Boolean = false,
    val photo: Int = R.drawable.pic1
)