package com.test.android15_ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import com.test.android15_ex02.databinding.ActivityMainBinding
import kotlin.concurrent.thread

lateinit var activityMainBinding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var firstNum:Int = 0
    var secondNum:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            thread {
                // onCreate 메서드의 수행이 끝날 때 까지 대기
                SystemClock.sleep(500)

                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(currentFocus,0)
            }
            editTextText.run {
                requestFocus()
            }

            button.run {
                setOnClickListener {
                    input()
                    textView.text = "$firstNum + $secondNum = ${firstNum+secondNum}"
                }
            }
            button2.run {
                setOnClickListener {
                    input()
                    textView.text = "$firstNum - $secondNum = ${firstNum-secondNum}"
                }
            }
            button3.run {
                setOnClickListener {
                    input()
                    textView.text = "$firstNum * $secondNum = ${firstNum*secondNum}"
                }
            }
            button4.run {
                setOnClickListener {
                    input()
                    textView.text = "$firstNum / $secondNum = ${firstNum/secondNum}"
                }
            }
        }
    }

    fun input(){
        val str1 = activityMainBinding.editTextText.text.toString()
        val str2 = activityMainBinding.editTextText2.text.toString()
        firstNum = str1.toInt()
        secondNum = str2.toInt()
    }
}