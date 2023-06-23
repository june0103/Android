package com.test.android43_activityforresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android43_activityforresult.databinding.ActivityFifthBinding
import com.test.android43_activityforresult.databinding.ActivityFourthBinding

class FifthActivity : AppCompatActivity() {

    lateinit var fifthBinding: ActivityFifthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fifthBinding = ActivityFifthBinding.inflate(layoutInflater)
        setContentView(fifthBinding.root)

        fifthBinding.run{
            buttonFifth.run{
                setOnClickListener {
                    // Activity 종료
                    finish()
                }
            }
        }
    }
}