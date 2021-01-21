package com.example.viewpagerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.viewpagerview.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val views:List<View> = listOf(CustomA(this), CustomB(this), CustomC(this), CustomD(this))

        val adapter = CustomPagerAdapter()
        adapter.views = views
        binding.viewPager.adapter = adapter
//        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
//            when (position) {
//                0 -> tab.text = "A"
//                1 -> tab.text = "B"
//                2 -> tab.text = "C"
//                else -> tab.text = "D"
//            }
//        }.attach()
    }
}