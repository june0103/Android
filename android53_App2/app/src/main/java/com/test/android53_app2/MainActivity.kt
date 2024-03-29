package com.test.android53_app2

import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    // BR 객체
    val r1 = App2Receiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 안드로이드 8.0 부터는 BR을 코드로 등록해야 다른 App에서 사용할 수 있다
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val filter = IntentFilter("com.test.testbr")
            registerReceiver(r1, filter)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // 등록된 BR 해제
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            unregisterReceiver(r1)
        }
    }
}