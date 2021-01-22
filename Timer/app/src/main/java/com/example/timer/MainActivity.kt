package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.timer.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var total = 0
    var started = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener {
            started = true
            thread(start = true) {
                while (started) {
                    Thread.sleep(1000)
                    if (started) {
                        total += 1
                        Handler(Looper.getMainLooper()).post{
                            val minute = String.format("%02d", total / 60)
                            val second = String.format("%02d", total % 60)
                            val time = "$minute:$second"
                            binding.textTimer.text = time
                        }

                    }
                }
            }
        }

        binding.buttonStop.setOnClickListener {
            if (started) {
                started = false
                total = 0
                binding.textTimer.text = getString(R.string.timer_text)
            }
        }


    }
}