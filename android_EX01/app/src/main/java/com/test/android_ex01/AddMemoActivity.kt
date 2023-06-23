package com.test.android_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.provider.ContactsContract.Data
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.test.android_ex01.databinding.ActivityAddMemoBinding
import kotlin.concurrent.thread

class AddMemoActivity : AppCompatActivity() {

    lateinit var addMemoBinding: ActivityAddMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addMemoBinding = ActivityAddMemoBinding.inflate(layoutInflater)
        setContentView(addMemoBinding.root)

        // 액션바를 사용하도록 설정
        supportActionBar?.setDisplayShowTitleEnabled(true)

        addMemoBinding.run {
            // key값인 카테고리명
            val ctgrName = intent.getStringExtra("ctgrName")

            // 액션바 제목 변경
            supportActionBar?.title = "${ctgrName}의 메모 추가"

            etAddmemoName.requestFocus()
            thread {
                SystemClock.sleep(300)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(etAddmemoName, 0)
            }

            btAddmemo.run {
                setOnClickListener {
                    val name = etAddmemoName.text.toString()
                    val content = etAddmemoContent.text.toString()
                    // 제목, 내용 유효성검사 토스트
                    if(name == ""){
                        Toast.makeText(this@AddMemoActivity,"메모 제목을 입력해주세요", Toast.LENGTH_SHORT).show()
                        etAddmemoName.requestFocus()
                        return@setOnClickListener
                    }
                    if(content == ""){
                        Toast.makeText(this@AddMemoActivity,"메모 내용을 입력해주세요", Toast.LENGTH_SHORT).show()
                        etAddmemoContent.requestFocus()
                        return@setOnClickListener
                    }
                    // key값인 카테고리명의 value(List)에 저장
                    val memo = MemoClass(name, content)
                    DataClass.ctgrMap[ctgrName]?.add(memo)

                    finish()
                }
            }
        }
    }
}