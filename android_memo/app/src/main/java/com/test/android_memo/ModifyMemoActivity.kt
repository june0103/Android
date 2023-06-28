package com.test.android_memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.test.android_memo.databinding.ActivityModifyMemoBinding
import kotlin.concurrent.thread

class ModifyMemoActivity : AppCompatActivity() {

    lateinit var modifyMemoBinding: ActivityModifyMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        modifyMemoBinding = ActivityModifyMemoBinding.inflate(layoutInflater)
        setContentView(modifyMemoBinding.root)

        // 액션바를 사용하도록 설정
        supportActionBar?.setDisplayShowTitleEnabled(true)

        modifyMemoBinding.run {
            // 수정할 메모의 카테고리명(key)
            val ctgrName = intent.getStringExtra("ctgrName")
            // 카테고리명에 저장된 메모의 위치
            val memoIndex = intent.getIntExtra("memoIndex",0)

            // 액션바 제목 변경
            supportActionBar?.title = "메모 : ${DataClass.ctgrMap[ctgrName]?.get(memoIndex)?.name} 수정"

            // 기존 메모 세팅
            etMemoModiName.setText(DataClass.ctgrMap[ctgrName]?.get(memoIndex)?.name)
            etMemoModiContent.setText(DataClass.ctgrMap[ctgrName]?.get(memoIndex)?.content)

            etMemoModiName.requestFocus()
            thread {
                SystemClock.sleep(300)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(etMemoModiName, 0)
            }

            btMemoModiOk.run {
                setOnClickListener {
                    val memoName = etMemoModiName.text.toString()
                    val memoContent = etMemoModiContent.text.toString()

                    // 토스트 활용 : 비어있지 않게
                    if(memoName == ""){
                        Toast.makeText(this@ModifyMemoActivity,"메모 제목을 입력해주세요", Toast.LENGTH_SHORT).show()
                        etMemoModiName.requestFocus()
                        return@setOnClickListener
                    }
                    if(memoContent == ""){
                        Toast.makeText(this@ModifyMemoActivity,"메모 내용을 입력해주세요", Toast.LENGTH_SHORT).show()
                        etMemoModiContent.requestFocus()
                        return@setOnClickListener
                    }

                    // key값에 저장된 list. 해당 index에 위치한 제목과 내용 업데이트
                    DataClass.ctgrMap[ctgrName]?.get(memoIndex)?.name  = memoName
                    DataClass.ctgrMap[ctgrName]?.get(memoIndex)?.content = memoContent

                    finish()
                }
            }

            btMemoModiCancel.run {
                setOnClickListener {
                    finish()
                }
            }


        }

    }
}