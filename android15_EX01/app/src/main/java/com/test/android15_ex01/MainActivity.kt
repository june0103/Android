package com.test.android15_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.test.android15_ex01.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    var gugudanList = mutableListOf<Int>()

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
            editText.run {
                // 포커스를 준다
                requestFocus()
                setOnEditorActionListener{
                        v, actionid, event ->
                    textView.text = ""
                    val dan = text.toString()
                    gugudan(dan.toInt())
                    var idx = 0
                    for(i in gugudanList){
                         idx++
                        textView.append("$dan X $idx = $i \n")
                    }
                    gugudanList.clear()
                    true
                }

            }

            button.run {
                setOnClickListener {
                    val dan = editText.text.toString()
                    var gugudan : Int
                    textView.text = ""
                    for( i in 1..9){
                        gugudan = i * dan.toInt()
                        textView.append("$dan X $i = $gugudan \n")
                    }
                    gugudanList.clear()
                }
            }

        }


        // information, 정보를 출력하는 용도
        Log.i("test_tag","테스트")
        // debug, 개발중에 출력해보고 싶은 것이 있을 때 사용
        Log.d("test_tag","테스틍")
        // error, 오류 메세지를 출력해보고 싶을 때 사용
        Log.e("test_tag","테스트에러")
        // warning, 경고 메세지를 출력해보고 싶을 때 사용
        Log.w("test_tag","테스트워닝")
        // verbose, 기타 용도로 사용
        Log.v("test_tag","테스트 verbose")
    }

    fun gugudan(input:Int){
        for(i in 1..9){
            gugudanList.add(i*input)
        }
    }

}