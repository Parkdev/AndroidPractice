package com.example.sayhello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sayhello.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)

        binding.textSay.text = "bindingTest"
        binding.btnSay.text = "Hello"
        binding.btnSay.setOnClickListener {
            if (binding.textSay.text == "bindingTest") {
                binding.textSay.text = "click1"
            } else {
                binding.textSay.text = "bindingTest"
            }
        }

        setContentView(binding.root)
    }
}