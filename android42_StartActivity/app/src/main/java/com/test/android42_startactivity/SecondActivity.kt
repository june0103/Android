package com.test.android42_startactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android42_startactivity.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var activitySecondBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySecondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(activitySecondBinding.root)

        activitySecondBinding.run {
            buttonSecond.run {
                setOnClickListener {
                    // 현재 Activity를 종료하고 BackStack 에서 제거
                    finish()
                }
            }
        }
    }
}