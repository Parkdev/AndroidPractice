package com.example.myvideochat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.quickblox.auth.session.QBSessionManager
import com.quickblox.auth.session.QBSettings


//Chat settings
const val USER_DEFAULT_PASSWORD = "quickblox"
const val CHAT_PORT = 5223
const val SOCKET_TIMEOUT = 300
const val KEEP_ALIVE: Boolean = true
const val USE_TLS: Boolean = true
const val AUTO_JOIN: Boolean = false
const val AUTO_MARK_DELIVERED: Boolean = true
const val RECONNECTION_ALLOWED: Boolean = true
const val ALLOW_LISTEN_NETWORK: Boolean = true

//App credentials
private const val APPLICATION_ID = "88710"
private const val AUTH_KEY = "55DQ-XFbVSLqv-L"
private const val AUTH_SECRET = "8Z5g3QUKKBD4a-p"
private const val ACCOUNT_KEY = "ZFeeiuQyi2N_sDHU-DCC"

//Chat credentials range
private const val MAX_PORT_VALUE = 65535
private const val MIN_PORT_VALUE = 1000
private const val MIN_SOCKET_TIMEOUT = 300
private const val MAX_SOCKET_TIMEOUT = 60000


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        QBSettings.getInstance().init(applicationContext, APPLICATION_ID, AUTH_KEY, AUTH_SECRET)
        QBSettings.getInstance().accountKey = ACCOUNT_KEY



    }
}