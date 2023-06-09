package com.test.android05_linearlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


//[공통]
//layout_width : 뷰의 가로길이. dp 단위 값, wrap_content, math_parent
//layout_height : 뷰의 세로길이, dp 단위 값, wrap_content, math_parent
//(wrap_conent : 자기를 완변하게 표현할 수 있는 최소 사이즈,  match_parent : 레이아웃 크기에 꽉 채운다)

//[LinearLayout]
//orientation : 뷰가 배치되는 모양. horizontal(좌측에서 우측으로, 생략), vertical(위에서 아래로)

//[LinearLayout에 배치되는 뷰]
//layout_weight : 뷰가 차지할 크기 비율. LinearLayout의 orientation 속성이 horizontal 이라면 가로크기를 조정하고 veritcal이면 세로 길이를 조정한다



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}