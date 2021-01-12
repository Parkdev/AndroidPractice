package com.example.grocerydelivery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.grocerydelivery.data.ProductData


class ProductInformationFragment : Fragment() {

    val args: ProductInformationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productCode = args.productCodeArgument

        val product = ProductData().allProducts().find { it.productCode == productCode }

        if (product != null) {
            view.findViewById<TextView>(R.id.productTitle).text = product.name
            view.findViewById<TextView>(R.id.productInfoDescription).text = product.description
        }

//        view.findViewById<TextView>(R.id.productTitle).text = args.productCodeArgument.toString()
//        view.findViewById<TextView>(R.id.productTitle).text = productCode
    }

}