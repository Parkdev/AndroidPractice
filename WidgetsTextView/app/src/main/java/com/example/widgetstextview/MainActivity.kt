package com.example.widgetstextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.SeekBar
import androidx.core.widget.addTextChangedListener
import com.example.widgetstextview.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    체크박스 그룹
    var listener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
        if (isChecked) {
            when (buttonView.id) {
                R.id.checkBoxApple -> Log.d("CheckBox", "사과가 선택되었습니다.")
                R.id.checkBoxBanana -> Log.d("CheckBox", "바나나가 선택되었습니다.")
                R.id.checkBoxOrange -> Log.d("CheckBox", "오렌지가 선택되었습니다.")
            }
        } else {
            when (buttonView.id) {
                R.id.checkBoxApple -> Log.d("CheckBox", "사과가 선택 해제되었습니다.")
                R.id.checkBoxBanana -> Log.d("CheckBox", "바나나가 선택 해제되었습니다.")
                R.id.checkBoxOrange -> Log.d("CheckBox", "오렌지가 선택 해제되었습니다.")
            }
        }

    }
//    프로그래스바 함수
    fun showProgress(show: Boolean) {
        if (show) {
            binding.progressLayout.visibility = View.VISIBLE
        } else {
            binding.progressLayout.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//      인풋 텍스트
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("EditText", "현재 입력된 값=${s.toString().length}")
            }
        })

//      라디오 박스

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radApple -> {
                    Log.d("RadioButton", "사과가 선택")
                    binding.editText.hint = "사과"
                }
                R.id.radBanana -> Log.d("RadioButton", "바나나가 선택")
                R.id.radOrange -> Log.d("RadioButton", "오렌지가 선택")
            }
        }
//      체크박스
////        체크박스 하나씩 적용하는 방법 (비효율적)
//        binding.checkBoxApple.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) Log.d("CheckBox", "사과가 선택되었습니다.")
//            else Log.d("CheckBox", "사과가 선택해제되었습니다.")
//        }

        binding.checkBoxApple.setOnCheckedChangeListener(listener)
        binding.checkBoxBanana.setOnCheckedChangeListener(listener)
        binding.checkBoxOrange.setOnCheckedChangeListener(listener)

//      프로그래스바

        thread(start = true) {
            Thread.sleep(3000)
            runOnUiThread {
                showProgress(false)
            }
        }

        //시크바
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.seekBartextView.text = "$progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                TODO("Not yet implemented")
            }
        })

//        레이팅 바
        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            binding.ratingBarTextView.text = "$rating"
        }


    }
}