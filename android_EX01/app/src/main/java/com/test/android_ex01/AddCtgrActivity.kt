package com.test.android_ex01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.test.android_ex01.databinding.ActivityAddCtgrBinding
import kotlin.concurrent.thread

class AddCtgrActivity : AppCompatActivity() {

    lateinit var activityAddCtgrBinding: ActivityAddCtgrBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAddCtgrBinding = ActivityAddCtgrBinding.inflate(layoutInflater)
        setContentView(activityAddCtgrBinding.root)

        // 액션바를 사용하도록 설정
        supportActionBar?.setDisplayShowTitleEnabled(true)

        // 액션바 제목 변경
        supportActionBar?.title = "카테고리 추가"

        activityAddCtgrBinding.run {

            etCtgrName.requestFocus()
            thread {
                SystemClock.sleep(300)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(etCtgrName, 0)
            }

            btCtgrAdd.run {
                setOnClickListener {
                    val name = etCtgrName.text.toString()
                    // 토스트활용 key값인 카테고리명 비어있지 않게 유효성검사
                    if(name == ""){
                        Toast.makeText(this@AddCtgrActivity,"카테고리 제목을 입력해주세요.", Toast.LENGTH_SHORT).show()
                        etCtgrName.requestFocus()
                        return@setOnClickListener
                    }

                    // 카테고리 이름은 map의 key로 사용하기 때문에 고유한 값을 가지기 위해 중복검사 실행 다이얼로그 활용
                    if(!DataClass.ctgrMap.containsKey(name)){
                        DataClass.ctgrMap[name] = mutableListOf()
                    }else{
                        val builder = AlertDialog.Builder(this@AddCtgrActivity)
                        builder.setTitle("중복된 카테고리")
                        builder.setMessage("카테고리 제목을 다시 입력해주세요")
                        builder.setPositiveButton("확인", null)
                        builder.show()
                        return@setOnClickListener
                    }
                    finish()
                }
            }

            btCtgrCancel.run {
                setOnClickListener {
                    finish()
                }
            }
        }

    }



}