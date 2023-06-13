package com.test.android29_addadapterviewitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.test.android29_addadapterviewitem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 데이터가 없는 리스트를 만든다
    val rowList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)



        activityMainBinding.run {

            listView.run {
                adapter = ArrayAdapter<String>(
                    this@MainActivity,android.R.layout.simple_list_item_1,rowList
                )
            }

            button.run {
                setOnClickListener {
                    // Adapter를 구성하기 위해 사용한 list에 데이터를 추가
                    val str1 = "row : ${System.currentTimeMillis()}"
                    rowList.add(str1)

                    textView.text = "항목 개수 : ${rowList.size}개"

                    // ListView의 adapter를 통해 갱신을 요청
                    val adapter = listView.adapter as ArrayAdapter<String>
                    adapter.notifyDataSetChanged()
                }
            }

            button2.run{
                setOnClickListener {
                    // 마지막 값 제거
                    rowList.removeLast()
                    textView.text = "항목 개수 : ${rowList.size}개"
                    val adapter = listView.adapter as ArrayAdapter<String>
                    adapter.notifyDataSetChanged()
                }
            }

        }

    }
}