package com.test.android_memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android_memo.databinding.ActivityMemoItemViewBinding

class MemoItemView : AppCompatActivity() {

    lateinit var memoItemViewBinding: ActivityMemoItemViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        memoItemViewBinding = ActivityMemoItemViewBinding.inflate(layoutInflater)
        setContentView(memoItemViewBinding.root)

        // 액션바를 사용하도록 설정
        supportActionBar?.setDisplayShowTitleEnabled(true)

        memoItemViewBinding.run {
            // 해당메모의 key값과 list의 index
            val ctgrName = intent.getStringExtra("ctgrName")
            val memoIndex = intent.getIntExtra("memoIndex",0)

            // 액션바 제목 변경
            supportActionBar?.title = "메모 : ${DataClass.ctgrMap[ctgrName]?.get(memoIndex)?.name}"

            tvMemoitemName.text = DataClass.ctgrMap[ctgrName]?.get(memoIndex)?.name
            tvMemoitemContent.text = DataClass.ctgrMap[ctgrName]?.get(memoIndex)?.content

            btMemoitem.run {
                setOnClickListener {
                    finish()
                }
            }
        }
    }



}