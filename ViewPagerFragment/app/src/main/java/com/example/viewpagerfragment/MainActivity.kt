package com.example.viewpagerfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.viewpagerfragment.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val fragmentList = arrayListOf<Fragment>(FragmentA(), FragmentB(), FragmentC(), FragmentD())

        val adapter = FragmentAdapter(fragmentList, supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "A"
                1 -> tab.text = "B"
                2 -> tab.text = "C"
                else -> tab.text = "D"
            }
        }.attach()
    }
}


//override fun getPageTitle(position: Int): CharSequence {
//    return when (position) {
//        0 -> "A"
//        1 -> "B"
//        2 -> "C"
//        else -> "D"
//    }
//}