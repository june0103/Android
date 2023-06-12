package com.test.android21_compountcomponent2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android21_compountcomponent2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.run {
                setOnClickListener {
                    // Toggle Button 의 On/Off 상태를 가져온다
                    if(toggleButton.isChecked){
                        textView.text = "Toggle On 상태"
                    } else{
                        textView.text = "Toggle Off 상태"
                    }

                    // Switch의 On/Off 상태를 가져온다
                    if(switch1.isChecked){
                        textView.append("Switch On 상태\n")
                    }else{
                        textView.append("Switch Off 상태\n")
                    }
                }
            }
            button2.run{
                setOnClickListener {
                    // Toggle Button 상태를 On상태로 설정
                    toggleButton.isChecked = true
                    // Switch 상태를 On상태로 설정
                    switch1.isChecked = true
                }
            }
            button3.run {
                setOnClickListener {
                    // Toggle Button 상태를 Off상태로 설정
                    toggleButton.isChecked = false
                    // Switch 상태를 Off상태로 설정
                    switch1.isChecked = false
                }
            }
            button4.run {
                setOnClickListener {
                    // Toggle Button 상태를 반전
                    toggleButton.toggle()
                    // Switch 상태를 반전
                    switch1.toggle()
                }
            }

            // Toggle Button 의 On/Off 상태가 변경될 때의 리스너
            toggleButton.run{
                setOnCheckedChangeListener { buttonView, isChecked ->

                    if(isChecked) {
                        textView2.text = "Toggle 버튼이 On상태"
                    } else{
                        textView2.text = "Toggle 버튼이 Off상태"
                    }

                }
                setOnCheckedChangeListener { compoundButton, b ->
                    
                }
            }

            // Switch 의 On/off 상태가 변경될 때의 리스너
            switch1.run {
                setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        textView2.append("Switch On 상태\n")
                    } else {
                        textView2.append("Switch Off 상태\n")
                    }
                }
            }


        }
    }
}