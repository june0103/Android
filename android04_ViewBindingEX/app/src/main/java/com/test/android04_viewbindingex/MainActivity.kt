package com.test.android04_viewbindingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android04_viewbindingex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.button2.setOnClickListener {
            var sum100 = 0
            for (a in 1..100){
                sum100 += a
            }
            activityMainBinding.textView2.text = "1 ~ 100까지의 총 합 : $sum100"
            println(sum100)
            sum100 = 0
        }

        activityMainBinding.button3.setOnClickListener {
            var sum200 = 0
            for (a in 101..200){
                println("$a   $sum200")
                sum200 += a
            }
            activityMainBinding.textView2.text = "101 ~ 200까지의 총 합 : $sum200"
            println(sum200)
            sum200 = 0
        }

    }
}