package com.example.videocallapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.Toast
import com.example.videocallapp.databinding.ActivityCallBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class CallActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCallBinding

    private var username = ""
    private var friendsUsername = ""

    private var isPeerConnected = false

    private var firebaseRef = Firebase.database.getReference("users")

    private var isAudio = true
    private var isVideo = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = intent.getStringExtra("username")!!

        binding.callBtn.setOnClickListener {
            friendsUsername = binding.friendNameEdit.text.toString()
            sendCallRequest()
        }

        binding.toggleAudioBtn.setOnClickListener {
            isAudio = !isAudio
            callJavascriptFunction("javascript:toggleAudio(\"${isAudio}\")")
            binding.toggleAudioBtn.setImageResource(if (isAudio) R.drawable.ic_baseline_mic_24 else R.drawable.ic_baseline_mic_off_24)
        }

        binding.toggleVideoBtn.setOnClickListener {
            isVideo = !isVideo
            callJavascriptFunction("javascript:toggleVideo(\"${isVideo}\")")
            binding.toggleVideoBtn.setImageResource(if (isVideo) R.drawable.ic_baseline_videocam_24 else R.drawable.ic_baseline_videocam_off_24)
        }

        setupWebView()




    }

    private fun sendCallRequest() {
        if (!isPeerConnected) {
            Toast.makeText(this, "연결을 확인하세요", Toast.LENGTH_LONG).show()
            return
        }

        firebaseRef.child(friendsUsername).child("incoming").setValue(username)
        firebaseRef.child(friendsUsername).child("isAvailable")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
//                연결요청을 허용했으면
                    if (snapshot.value.toString() == "true") {
                        listenForConnId()
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }

    private fun listenForConnId() {
        firebaseRef.child(friendsUsername).child("connId")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.value == null)
                        return
                    switchToControls()
                    Log.d("스냅샷밸류", snapshot.value.toString())
                    callJavascriptFunction("javascript:startCall(\"${snapshot.value}\")")
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }

    private fun setupWebView() {
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest?) {
                request?.grant(request.resources)
            }
        }
        binding.webView.settings.setJavaScriptEnabled(true)
        binding.webView.settings.mediaPlaybackRequiresUserGesture = false
        binding.webView.addJavascriptInterface(JavascriptInterface(this), "Android")

        loadVideoCall()
    }

    private fun loadVideoCall() {
        val filePath = "file:android_asset/call.html"
        binding.webView.loadUrl(filePath)

        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                initializePeer()
            }
        }
    }

    //  #onCallRequest setvalue를 위한 변수
    var uniqueId = ""

    //  js기능의 peer초기화
    private fun initializePeer() {

        uniqueId = getUniqueID()
        callJavascriptFunction("javascript:init(\"${uniqueId}\")")
        firebaseRef.child(username).child("incoming")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    onCallRequest(snapshot.value as? String)
                }

                override fun onCancelled(error: DatabaseError) {}

            })
    }

    private fun onCallRequest(caller: String?) {
        if (caller == null) return

        binding.callLayout.visibility = View.VISIBLE
        binding.incomingCallTxt.text = "$caller 이 연락을 원합니다."

        binding.acceptBtn.setOnClickListener {
            firebaseRef.child(username).child("connId").setValue(uniqueId)
            firebaseRef.child(username).child("isAvailable").setValue(true)

            binding.callLayout.visibility = View.GONE
            switchToControls()
        }

        binding.rejectBtn.setOnClickListener {
            firebaseRef.child(username).child("incoming").setValue(null)
            binding.callLayout.visibility = View.GONE
        }

    }

    private fun switchToControls() {
        binding.inputLayout.visibility = View.GONE
        binding.callControlLayout.visibility = View.VISIBLE
    }

    //  peer초기화를 위해 unique id를 가져오는 기능
    private fun getUniqueID(): String {
        return UUID.randomUUID().toString()
    }

    //  자바스크립트 불러오
    private fun callJavascriptFunction(functionString: String) {
        binding.webView.post { binding.webView.evaluateJavascript(functionString, null) }
    }

    //  Peer연결 확인후 변수 변경
    fun onPeerConnected() {
        isPeerConnected = true
    }

    //    백버튼 누르면 액티비티 제거
    override fun onBackPressed() {
        finish()
    }

    //    액티비티 제거시 파이어베이스의 유저정보도 삭제
    override fun onDestroy() {
        firebaseRef.child(username).setValue(null)
        binding.webView.loadUrl("about:blank")
        super.onDestroy()
    }
}