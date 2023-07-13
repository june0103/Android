package com.test.android79_imageresource

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android79_imageresource.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            buttonStartAni.setOnClickListener {
                // 애니메이션 이미지 객체 추출
                val animationDrawable = imageViewAni.drawable as AnimationDrawable

                if(animationDrawable.isRunning == true){
                    // 애니메이션 중단
                    animationDrawable.stop()
                } else{
                    // 애니메이션 가동
                    animationDrawable.start()
                }
            }
        }
    }
}