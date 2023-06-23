package com.test.android_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.provider.ContactsContract.Data
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.test.android_ex01.databinding.ActivityModifyCtgrBinding
import kotlin.concurrent.thread

class ModifyCtgrActivity : AppCompatActivity() {

    lateinit var modifyCtgrBinding: ActivityModifyCtgrBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        modifyCtgrBinding = ActivityModifyCtgrBinding.inflate(layoutInflater)
        setContentView(modifyCtgrBinding.root)

        // 액션바를 사용하도록 설정
        supportActionBar?.setDisplayShowTitleEnabled(true)

        modifyCtgrBinding.run {
            // 수정할 카테고리 map의 key값
            val ctgrName = intent.getStringExtra("ctgrName")

            // 액션바 제목 변경
            supportActionBar?.title = "카테고리 $ctgrName 수정"

            // 기존 카테고리 이름
            tvCtgrModiName.text = ctgrName

            etCtgrModiName.requestFocus()
            thread {
                SystemClock.sleep(300)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(etCtgrModiName, 0)
            }

            btCtgrModifyOk.run {
                setOnClickListener {
                    // 기존카테고리
                    val oldkey = ctgrName
                    // 새로운카테고리
                    val newKey = etCtgrModiName.text.toString()

                    // 카테고리 이름이 비어있는지
                    if(newKey == ""){
                        Toast.makeText(this@ModifyCtgrActivity,"카테고리 제목을 입력해주세요", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    if (oldkey == newKey) {
                        // 새로운 키와 기존 키가 동일한 경우 수정 없이 종료
                        finish()
                    } else{
                        // key의 value 값 삭제시키지 않고 유지하기 위해 (카테고리 이름에 해당하는 메모리스트)
                        val value = DataClass.ctgrMap[oldkey]
                        // value에 값이 있을 때
                        if(value!= null){
                            if (value.isNotEmpty()) {
                                // 고유key 중복검사 - 중복된 key가 없다면
                                if(!DataClass.ctgrMap.containsKey(newKey)){
                                    // 비어있지 않은 리스트인 경우, 새로운 key로 맵 업데이트
                                    DataClass.ctgrMap[newKey] = value
                                    // 기존 key 삭제
                                    DataClass.ctgrMap.remove(oldkey)
                                }else{
                                    // 중복된 key가 있다면 다이얼로그
                                    val builder = AlertDialog.Builder(this@ModifyCtgrActivity)
                                    builder.setTitle("중복된 카테고리")
                                    builder.setMessage("카테고리 제목을 다시 입력해주세요")
                                    builder.setPositiveButton("확인", null)
                                    builder.show()
                                    return@setOnClickListener
                                }
                            } else {
                                // 고유key값 중복검사
                                if(!DataClass.ctgrMap.containsKey(newKey)){
                                    // 리스트가 비어있는 경우, 비어있는list를 가진 key로 맵 업데이트
                                    DataClass.ctgrMap[newKey] = mutableListOf()
                                    DataClass.ctgrMap.remove(oldkey)
                                }else{
                                    val builder = AlertDialog.Builder(this@ModifyCtgrActivity)
                                    builder.setTitle("중복된 카테고리")
                                    builder.setMessage("카테고리 제목을 다시 입력해주세요")
                                    builder.setPositiveButton("확인", null)
                                    builder.show()
                                    return@setOnClickListener
                                }
                            }
                        }
                    }
                    finish()
                }
            }

            btCtgrModifyCancel.run {
                setOnClickListener {
                    finish()
                }
            }
        }

    }
}