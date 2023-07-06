package com.test.android66_codeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.test.android66_codeview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    // 생성된 뷰들을 담을 리스트
    val viewList = mutableListOf<EditText>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.setOnClickListener {
                // View의 가로 세로 길이
                val params = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                // EditText생성
                val newEditText = EditText(this@MainActivity)

                // 설정
                newEditText.layoutParams = params
                newEditText.hint = "힌트설정"

                // 생성한 뷰 추가
                activityMainBinding.mainContainer.addView(newEditText)
                viewList.add(newEditText)
            }

            button2.setOnClickListener {
                textView.text = ""
                // 리스트에 들어있는 뷰의 수만큼 반복
                for(v1 in viewList){
                    textView.append("${v1.text.toString()}\n")
                }
            }

            button3.setOnClickListener {
                // 순서 지정하여 제걱
                activityMainBinding.mainContainer.removeViewAt(viewList.size - 1)
                viewList.removeLast()

                // View를 지정하여 제거
//                val lastView = viewList.last()
//                activityMainBinding.mainContainer.removeView(lastView)
//                viewList.removeLast()
            }

            button4.setOnClickListener {
                // 모든 뷰를 제거한다.
                activityMainBinding.mainContainer.removeAllViews()
            }
        }
    }
}