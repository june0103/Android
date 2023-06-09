package com.test.android19_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import com.test.android19_ex01.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val studentList = mutableListOf<Student>()
//        val hobbyList = arrayListOf<String>()

        activityMainBinding.run {
            thread {
                SystemClock.sleep(500)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(currentFocus, 0)
            }
            etName.requestFocus()


            etKorean.run {
                setOnEditorActionListener { v, actionid, event ->
                    val hobbyList = arrayListOf<String>()
                    val name = etName.text.toString()
                    val age = etAge.text.toString().toInt()
                    var gender = ""
                    when (radioGroup.checkedRadioButtonId) {
                        R.id.radioButton_male -> {
                            gender = "남성"
                        }

                        R.id.radioButton_female -> {
                            gender = "여성"
                        }
                    }
                    if (checkBoxGame.isChecked) {
                        hobbyList.add("게임")
                    }
                    if (checkBoxSoccer.isChecked) {
                        hobbyList.add("축구")
                    }
                    if (checkBoxVideo.isChecked) {
                        hobbyList.add("영상 시청")
                    }
                    val korean = etKorean.text.toString().toInt()

                    // enter누를 때 체크된 값들은 리스트에 정상으로 저장되어있는상태 테스트
//                    tvList.text = hobbyList.toString()

                    // 마지막 취미리스트가 모든 학생의 취미리스트값으로 덮어씌어지며 저장되는 문제
                    val student = Student(name, age, gender, hobbyList, korean)
                    studentList.add(student)

                    etName.setText("")
                    etAge.setText("")
                    checkBoxGame.isChecked = false
                    checkBoxSoccer.isChecked = false
                    checkBoxVideo.isChecked = false
                    radioButtonMale.isChecked = false
                    radioButtonFemale.isChecked = false
                    etKorean.setText("")

                    // 저장된 학생리스트 출력테스트
//                    textView7.text = studentList.toString()

//                    hobbyList.clear()
                    etName.requestFocus()
                }
            }

            button.run {
                setOnClickListener {
                    tvList.text = ""
                    var koreanSum = 0
                    for (student in studentList) {
                        student.run {
                            tvList.append("이름 : $name\n")
                            tvList.append("나이 : $age\n")
                            tvList.append("성별 : $gender\n")
                            tvList.append("취미 : ${hobby.toString()}\n")
                            tvList.append("국어점수 : $korean\n")
                            tvList.append("-----------------------------\n")
                            koreanSum += korean
                        }
                    }
                    tvList.append("국어 총점 : $koreanSum\n")
                    tvList.append("국어 평균 : ${koreanSum / studentList.size}\n")
                }
            }

        }
    }
}

data class Student(
    var name: String,
    var age: Int,
    var gender: String,
    var hobby: List<String>,
    var korean: Int
)