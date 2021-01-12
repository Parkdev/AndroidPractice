package com.example.grocerydelivery.data

data class StoreItem(
    val productCode: String,
    val name: String,
    val price: Double,
    val description: String,
    val isHighlyRated: Boolean = false
)