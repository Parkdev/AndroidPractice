package com.example.grocerydelivery.data

import com.example.grocerydelivery.R

class ProductData {

    fun allProducts(): List<StoreItem> {

        return listOf(
            StoreItem("a", "Ubuntu", 1.99, "Awesome"),
            StoreItem("b", "Ubuntu2", 2.99, "Awesome2", true, photo = R.drawable.pic2),
            StoreItem("c", "Ubuntu3", 3.99, "Awesome3", photo = R.drawable.pic3),
            StoreItem("d", "Ubuntu4", 4.99, "Awesome4", true, photo = R.drawable.pic4),
            StoreItem("e", "Ubuntu5", 5.99, "Awesome5", photo = R.drawable.pic5),
            StoreItem("f", "Ubuntu6", 6.99, "Awesome6")
        )
    }
}