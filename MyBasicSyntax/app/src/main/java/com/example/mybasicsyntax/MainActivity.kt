package com.example.mybasicsyntax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mybasicsyntax.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

//        var now = 8
//        when (now) {
//            9,10 -> {
//                binding.mainText.text = "1"
//            }
//            in 11..20 -> {
//                binding.mainText.text = "2"
//            }
//            else -> {
//                binding.mainText.text = "3"
//            }
//        }
        setContentView(binding.root)
    }
}