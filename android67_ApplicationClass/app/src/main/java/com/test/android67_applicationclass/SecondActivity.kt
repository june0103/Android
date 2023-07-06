package com.test.android67_applicationclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android67_applicationclass.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var secondBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(secondBinding.root)

        secondBinding.run {
            // ApplicationClass 객체를 가져온다.
            val appClass = application as AppClass

            textViewSecond.text = "${appClass.value1}\n"
            textViewSecond.append("${appClass.value2}\n")
            textViewSecond.append("${appClass.value3}")
        }


    }
}