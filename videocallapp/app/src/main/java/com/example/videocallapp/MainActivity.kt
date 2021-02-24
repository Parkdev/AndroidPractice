package com.example.videocallapp

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.videocallapp.databinding.ActivityMainBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

//peerjs --port 9000 --key peerjs --path /videocallapp 로 서버 킨후 실행

class MainActivity : AppCompatActivity() {

    private val permissions =
        arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO)
    private val requestCode = 1

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!isPermissionGranted()) {
            askPermissions()
        }

        Firebase.initialize(this)

        binding.loginBtn.setOnClickListener {
            val username = binding.usernameEdit.text.toString()
            val intent = Intent(this, CallActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        }


    }

    private fun askPermissions() {
        ActivityCompat.requestPermissions(this, permissions, requestCode)
    }

    private fun isPermissionGranted(): Boolean {

        permissions.forEach {
            if (ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED)
                return false
        }

        return true
    }
}