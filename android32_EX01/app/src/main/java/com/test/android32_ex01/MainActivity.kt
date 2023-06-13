package com.test.android32_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import android.widget.SimpleAdapter
import com.test.android32_ex01.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    val nameData = mutableListOf<String>()
    val ageData = mutableListOf<Int>()
    val koreanData = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
//            thread {
//                SystemClock.sleep(500)
//                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//                imm.showSoftInput(currentFocus, 0)
//            }

            etKorean.run {
                setOnEditorActionListener { textView, i, keyEvent ->
                    val name = etName.text.toString()
                    nameData.add(name)
                    val age = etAge.text.toString().toInt()
                    ageData.add(age)
                    val korean = etKorean.text.toString().toInt()
                    koreanData.add(korean)

                    val dataList = ArrayList<HashMap<String,Any>>()

                    for(idx in 0 until nameData.size){
                        val map = HashMap<String,Any>()
                        map["name"] = nameData[idx]
                        map["age"] = ageData[idx]
                        map["korean"] = koreanData[idx]

                        dataList.add(map)
                    }
                    etName.setText("")
                    etAge.setText("")
                    etKorean.setText("")
                    clearFocus()
                    val keys = arrayOf("name","age","korean")
                    val idx = intArrayOf(R.id.textViewRow1, R.id.textViewRow2, R.id.textViewRow3)

                    val simpleAdapter = SimpleAdapter(this@MainActivity, dataList, R.layout.row, keys, idx)
                    listView.run {
                        adapter = simpleAdapter
                    }

                    false
                }
            }

        }

    }
}