package com.test.android17_textinputlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.test.android17_textinputlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {

            textInputLayout.run {
                editText?.run {
                    addTextChangedListener {
                        if(it!!.length > 10){
                            error = "10 글자 이하로 입력"
                            textInputLayout.error = "10 글자 이하로 입력"
                        } else{
                            error = null
                            textInputLayout.error = null
                        }
                    }
                }
            }

            button.run {
                setOnClickListener {
                    // 입력한 내용 가져오기
                    val str1 = textInputEditText.text.toString()
                    //textView.text = str1

                    // TextInputLayout 로 부터 EditText를 추출
                    val editText = textInputLayout.editText!!.text.toString()
                    textView.text = editText
                }
            }
        }

    }
}