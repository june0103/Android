package com.test.android22_checkedtextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android22_checkedtextview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            checkedTextView.run {
                setOnClickListener {
                    // 현재 체크 상태를 반전
                    toggle()
                }
            }
            checkedTextView2.run {
                setOnClickListener {
                    toggle()
                }
            }
            button.run {
                setOnClickListener {
                    checkedTextView.isChecked = true
                    checkedTextView2.isChecked = true
                }
            }
            button2.run {
                setOnClickListener {
                    checkedTextView.isChecked = false
                    checkedTextView2.isChecked = false
                }
            }
            button3.run {
                setOnClickListener {
                    textView.text = ""
                    if (checkedTextView.isChecked) {
                        textView.append("CheckBox1이 체크 상태\n")
                    } else {
                        textView.append("CheckBox1이 체크 안된 상태\n")
                    }
                    if (checkedTextView2.isChecked) {
                        textView.append("CheckBox2이 체크 상태\n")
                    } else {
                        textView.append("CheckBox2이 체크 안된 상태\n")
                    }
                    if(checkedTextView3.isChecked){
                        textView.append("Radio1이 선택되었습니다\n")
                    } else if(checkedTextView4.isChecked){
                        textView.append("Radio2가 선택되었습니다\n")
                    } else if(checkedTextView5.isChecked){
                        textView.append("Radio3이 선택되었습니다\n")
                    }
                }
            }
            checkedTextView3.run {
                setOnClickListener {
                    checkedTextView3.isChecked = true
                    checkedTextView4.isChecked = false
                    checkedTextView5.isChecked = false
                }
            }
            checkedTextView4.run {
                setOnClickListener {
                    checkedTextView3.isChecked = false
                    checkedTextView4.isChecked = true
                    checkedTextView5.isChecked = false
                }
            }
            checkedTextView5.run {
                setOnClickListener {
                    checkedTextView3.isChecked = false
                    checkedTextView4.isChecked = false
                    checkedTextView5.isChecked = true
                }

            }
        }
    }
}