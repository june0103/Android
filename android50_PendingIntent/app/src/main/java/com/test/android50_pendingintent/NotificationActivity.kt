package com.test.android50_pendingintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android50_pendingintent.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {

    lateinit var activityNotification1Binding : ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNotification1Binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(activityNotification1Binding.root)

        activityNotification1Binding.run {
            val data1 = intent.getIntExtra("data1",0)
            val data2 = intent.getStringExtra("data2")

            textViewNo1.run {
                text = "data1 : ${data1}\n"
                append("data2 : ${data2}")
            }
        }


    }
}