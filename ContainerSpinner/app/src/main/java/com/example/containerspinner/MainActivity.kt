package com.example.containerspinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.containerspinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var data = listOf("- 선택하세요 -", "1월", "2월", "3월", "4월", "5월", "6월")
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                binding.result.text = data[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


    }
}