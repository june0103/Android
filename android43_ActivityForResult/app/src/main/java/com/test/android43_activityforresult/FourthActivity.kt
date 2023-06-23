package com.test.android43_activityforresult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android43_activityforresult.databinding.ActivityFourthBinding
import com.test.android43_activityforresult.databinding.ActivitySecondBinding

class FourthActivity : AppCompatActivity() {

    lateinit var fourthBinding: ActivityFourthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fourthBinding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(fourthBinding.root)

        fourthBinding.run{
            textViewFourth.run {
                val data1 = intent.getIntExtra("data1", 0)
                val data2 = intent.getDoubleExtra("data2", 0.0)

                text = "data1 : ${data1}\n"
                append("data2 : ${data2}\n")
            }

            buttonFourth.run{
                setOnClickListener {
                    val resultIntent = Intent()
                    resultIntent.putExtra("value1", 200)
                    resultIntent.putExtra("value2", 22.22)

                    // 돌아갈 때 전달 할 값이 있다면
                    // setResult 메서드의 두 번째 매개변수에 Intent 객체를 넣어준다.
                    setResult(RESULT_OK,resultIntent)
                    // Activity 종료
                    finish()
                }
            }
        }
    }
}