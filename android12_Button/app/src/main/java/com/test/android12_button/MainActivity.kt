package com.test.android12_button

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android12_button.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        activityMainBinding.run {
            button2.run {
                // 버튼의 문자열 벼경
                text = "버튼"
                // 눌렀을 때 반응하는 리스너 설정
                setOnClickListener{
                    activityMainBinding.textView.text="버튼을 눌렀습니다"
                }
            }
            imageButton2.run {
                // 이미지 버튼의 이미지를 변경
                setImageResource(R.drawable.imgflag8)
                // 눌렀을 때 반응하는 리스너 설정
                setOnClickListener {
                    activityMainBinding.textView.text = "이미지버튼을 눌렀습니다"
                }
            }
        }

    }
}