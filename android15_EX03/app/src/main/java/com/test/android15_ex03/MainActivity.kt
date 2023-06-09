package com.test.android15_ex03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.test.android15_ex03.databinding.ActivityMainBinding
import kotlin.concurrent.thread

lateinit var activityMainBinding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val studentList = mutableListOf<Student>()

        activityMainBinding.run {
            thread {
                // onCreate 메서드의 수행이 끝날 때 까지 대기
                SystemClock.sleep(500)

                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(currentFocus, 0)
            }
            etName.run {
                requestFocus()
                setOnEditorActionListener { v, actionid, event ->

                    false
                }
            }
            etAge.run {
                setOnEditorActionListener { v, actionid, event ->

                    false
                }
            }
            etKorean.run {
                setOnEditorActionListener { v, actionid, event ->

                    false
                }
            }
            etEnglish.run {
                setOnEditorActionListener { v, actionid, event ->

                    false
                }
            }
            etMath.run {
                setOnEditorActionListener { v, actionid, event ->
                    val name = etName.text.toString()
                    val age = etAge.text.toString().toInt()
                    val korean = etKorean.text.toString().toInt()
                    val english = etEnglish.text.toString().toInt()
                    val math = etMath.text.toString().toInt()
                    val student = Student(name, age, korean, english, math)
                    studentList.add(student)

                    etName.setText("")
                    etAge.setText("")
                    etKorean.setText("")
                    etEnglish.setText("")
                    etMath.setText("")
                    textView.text = "학생수 : ${studentList.size} 명"
                    etName.requestFocus()
                }
            }
            button.run {
                setOnClickListener {
                    Log.d("stduentInfo", "gg")
                    var koreanSum = 0
                    var englishSum = 0
                    var mathSum = 0
                    for (student in studentList) {

                        student.run {
                            Log.d("studentInfo", "이름 : $name")
                            Log.d("studentInfo", "나이 : $age")
                            Log.d("studentInfo", "국어 점수 : $korean")
                            Log.d("studentInfo", "영어 점수 : $english")
                            Log.d("studentInfo", "수학 점수 : $math")
                        }

//                        Log.d("studentInfo", "이름 : ${student.name}")
//                        Log.d("studentInfo", "나이 : ${student.age}")
//                        Log.d("studentInfo", "국어 점수 : ${student.korean}")
//                        Log.d("studentInfo", "영어 점수 : ${student.english}")
//                        Log.d("studentInfo", "수학 점수 : ${student.math}")
                        koreanSum += student.korean
                        englishSum += student.english
                        mathSum += student.math
                        Log.d("studentInfo", "------------------------")
                    }
                    Log.d("studentInfo", "국어 총점 : $koreanSum")
                    Log.d("studentInfo", "영어 총점 : $englishSum")
                    Log.d("studentInfo", "수학 총점 : $mathSum")
                    Log.d("studentInfo", "국어 평균 : ${koreanSum / studentList.size}")
                    Log.d("studentInfo", "영어 평균 : ${englishSum / studentList.size}")
                    Log.d("studentInfo", "수학 평균 : ${mathSum / studentList.size}")
                }

            }
        }

    }
}

data class Student(var name: String, var age: Int, var korean: Int, var english: Int, var math: Int)