package com.test.android44_ex01

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.test.android44_ex01.databinding.ActivityAddBinding
import kotlin.concurrent.thread

class AddActivity : AppCompatActivity() {

    lateinit var activityAddBinding: ActivityAddBinding
    val dataList = arrayOf("수박", "사과", "귤")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAddBinding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(activityAddBinding.root)

        activityAddBinding.run {
            spinner.run {
                val a1 = ArrayAdapter<String>(
                    this@AddActivity,
                    // Spinner 접혀 있을 때 모양
                    R.layout.simple_spinner_item,
                    dataList
                )
                // spinner 펼쳐져 있을 때 모양
                a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                adapter = a1

                // Spinner의 항목을 코드로 선택
                // 0부터 시작하는 순서값을 넣어준다.
                setSelection(0)
            }


            etNum.requestFocus()
            thread {
                SystemClock.sleep(500)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(etNum, 0)
            }

            etCountry.run {
                setOnEditorActionListener { textView, i, keyEvent ->
                    val kind = dataList[spinner.selectedItemPosition]
                    val num = etNum.text.toString().toInt()
                    val country = etCountry.text.toString()

                    val fluitClass = FruitClass(kind,num,country)

                    DataClass.fruitList.add(fluitClass)

                    finish()
                    false

                }
            }


            btAdd.run {
                setOnClickListener {
//                    val kind = dataList[spinner.selectedItemPosition]
//                    val num = etNum.text.toString().toInt()
//                    val country = etCountry.text.toString()
//
//                    val resultIntent = Intent()
//                    resultIntent.putExtra("kind",kind)
//                    resultIntent.putExtra("num",num)
//                    resultIntent.putExtra("country",country)
//                    setResult(RESULT_OK,resultIntent)
//
//                    etNum.setText("")
//                    etCountry.setText("")

                    finish()
                }
            }
        }
    }


}