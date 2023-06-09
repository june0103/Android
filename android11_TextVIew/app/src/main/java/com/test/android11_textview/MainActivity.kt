package com.test.android11_textview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android11_textview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            textView2.run {
                // 문자열 설정
                text = "문자열"

                // 배경 색상 지정
//                setBackgroundColor(Color.RED)
//                setBackgroundColor(Color.rgb(0,255,191))
                //                             투명도
                setBackgroundColor(Color.argb(50,145,12,52))

                // 글자 색상
                setTextColor(Color.RED)

                // 새로운 문자열 설정
                text = "새로운 문자열"

                // 문자열 추가
                append("추가된 문자열1")
                append("추가된 문자열2")
            }
        }


    }
}