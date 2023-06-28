package com.test.android52_thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import com.test.android52_thread.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.setOnClickListener {
                val now = System.currentTimeMillis()
                textView.text = "now : $now"
            }
        }

//        // 100ms 마다 한번씩 현재 시간을 가져와 출력
//        while(true){
//            SystemClock.sleep(100)
//            val now2 = System.currentTimeMillis()
//            activityMainBinding.textView2.text = "now2 : $now2"
//        }

        // 사용자가 발생시킨 쓰레드(화면 관련 작업 금지)
        thread {
            while (true){
                SystemClock.sleep(100)
                val now2 = System.currentTimeMillis()
                Log.d("now2","now2 : $now2")
                // 자바에서는 오류나서 runOnUiThread안에 작성해야하는것 같은데
                // 사용자가 발생시킨 쓰레드 안에서 화면 관련된 작업이 필요하다면 ronOnUiThread를 사용
                // runOnUiThread안에 만들어놓은 코드는 MainThread가 처리하도록 요청
                // Kotlin 기반으로 안드로이드 어플리케이션을 제작할 경우 runOnUiThread를 사용하는것을 미리 구현해놨기때문에 사용하지 않아도 된다
                runOnUiThread {
                    activityMainBinding.textView2.text = "now2 : $now2"
                }

            }
        }
    }
}