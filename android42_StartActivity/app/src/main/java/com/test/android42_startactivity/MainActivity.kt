package com.test.android42_startactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android42_startactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            buttonMain.run {
                setOnClickListener{
                    // 다른 Activity를 실행
                    // Intent 객체를 생성
                    val secondIntent = Intent(this@MainActivity, SecondActivity::class.java)
                    startActivity(secondIntent)
                }

            }
        }
    }
}