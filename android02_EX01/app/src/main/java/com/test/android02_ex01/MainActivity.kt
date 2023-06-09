package com.test.android02_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var textView6: TextView
    lateinit var button_plus : Button
    lateinit var button_minus : Button
    lateinit var button_multiply : Button
    lateinit var button_divide : Button
    val a = 10
    val b = 10
    var c = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        textView6 = findViewById(R.id.textView6)
        button_plus = findViewById(R.id.button_plus)
        button_minus = findViewById(R.id.button_minus)
        button_multiply = findViewById(R.id.button_multiply)
        button_divide = findViewById(R.id.button_divide)

        val button_ClickListener = Button_ClickListener()
        button_plus.setOnClickListener(button_ClickListener)

        button_minus.setOnClickListener(button_ClickListener)

        button_multiply.setOnClickListener(button_ClickListener)

        button_divide.setOnClickListener(button_ClickListener)

    }
    inner class Button_ClickListener : OnClickListener{
        override fun onClick(p0: View?) {
            when(p0?.id){
                button_plus.id -> {
                    c = a + b
                    textView6.text = "+"
                    textView.text = "$c"
                }

                button_minus.id -> {
                    c = a - b
                    textView6.text = "-"
                    textView.text = "$c"
                }

                button_multiply.id -> {
                    c = a * b

                    textView6.text = "*"
                    textView.text = "$c"
                }

                button_divide.id -> {
                    c = a / b

                    textView6.text = "/"
                    textView.text = "$c"
                }
            }
        }
    }



}