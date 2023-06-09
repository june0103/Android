package com.test.android14_logcat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // LogCat : 안드로이드 스튜디오에서 에뮬레이터나 단말기에서 사용하는 콘솔
        // 일반출력
        // tag를 System.in 으로 설정하여 Log.i 메서드로 Logcat에 출력한다
        println("안녕하세요")

        // information, 정보를 출력하는 용도
        Log.i("test_tag","테스트")
        // debug, 개발중에 출력해보고 싶은 것이 있을 때 사용
        Log.d("test_tag","테스틍")
        // error, 오류 메세지를 출력해보고 싶을 때 사용
        Log.e("test_tag","테스트에러")
        // warning, 경고 메세지를 출력해보고 싶을 때 사용
        Log.w("test_tag","테스트워닝")
        // verbose, 기타 용도로 사용
        Log.v("test_tag","테스트 verbose")
    }
}