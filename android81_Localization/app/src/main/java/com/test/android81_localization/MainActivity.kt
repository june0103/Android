package com.test.android81_localization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android81_localization.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            textView2.setText(R.string.str1)
            imageView2.setImageResource(R.drawable.flag1)
        }
    }
}