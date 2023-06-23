package com.test.android43_activityforresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android43_activityforresult.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    lateinit var thirdBinding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        thirdBinding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(thirdBinding.root)

        thirdBinding.run{


            buttonThird.run{
                setOnClickListener {
                    // Activity 종료
                    finish()
                }
            }
        }
    }
}