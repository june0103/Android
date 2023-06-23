package com.test.android47_dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import com.test.android47_dialog.databinding.ActivityMainBinding
import com.test.android47_dialog.databinding.DialogBinding
import java.util.Calendar
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val dateList = arrayOf(
        "항목1", "항목2", "항목3", "항목4", "항목5", "항목6",
        "항목7", "항목8", "항목9", "항목10", "항목11", "항목12",
        "항목13", "항목14", "항목15", "항목16", "항목17", "항목18")

    val multiChoiceList = BooleanArray(dateList.size){i -> false}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.run {
                setOnClickListener {
                    // 다이얼로그 생성을 위한 객체 생성
                    val builder = AlertDialog.Builder(this@MainActivity)

                    // 타이틀
                    builder.setTitle("기본 다이얼로그")
                    // 메시지
                    builder.setMessage("기본 다이얼로그 입니다")
                    // 아이콘
                    builder.setIcon(R.mipmap.ic_launcher)

                    // 버튼 배치 ( 총 3개 가능 )
                    //builder.setPositiveButton("Positive", null)
                    //builder.setNegativeButton("Negative",null)
                    //builder.setNeutralButton("Neutral",null)

                    // 버튼 클릭시 그냥 닫아주는 용도라면 리스너 달아주지 않아도 괜찮다
                    builder.setPositiveButton("Positive"){ dialogInterface: DialogInterface, i: Int ->
                        textView.text = "Positive 버튼을 눌렀습니다"
                    }
                    builder.setNegativeButton("Negative"){ dialogInterface: DialogInterface, i: Int ->
                        textView.text = "Negative 버튼을 눌렀습니다"
                    }
                    builder.setNeutralButton("Neutral"){ dialogInterface: DialogInterface, i: Int ->
                        textView.text = "Neutral 버튼을 눌렀습니다"
                    }

                    // 다이얼로그 띄우기
                    builder.show()
                }
            }

            button2.run {
                setOnClickListener {

                    val dialogBinding = DialogBinding.inflate(layoutInflater)
                    val builder = AlertDialog.Builder(this@MainActivity)
                    builder.setTitle("커스텀 다이얼로그")
                    builder.setIcon(R.mipmap.ic_launcher)

                    // 새로운 뷰를 설정
                    builder.setView(dialogBinding.root)

                    dialogBinding.editTextDialog1.requestFocus()

                    thread{
                        SystemClock.sleep(300)
                        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(dialogBinding.editTextDialog1,0)
                    }


                    builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                        // 입력한 내용 가져오기
                        val str1 = dialogBinding.editTextDialog1.text.toString()
                        val str2 = dialogBinding.editTextDialog2.text.toString()

                        textView.text = "$str1 \n"
                        textView.append(str2)
                    }
                    builder.setNegativeButton("취소",null)
                    builder.show()
                }
            }

            button3.setOnClickListener {
                // 날짜 선택하기 위해 사용하는 다이얼로그
                // 현재 날짜를 지정해줘야한다

                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                // 날짜 선택하면 동작할 리스너
                val datePicerListener = object : DatePickerDialog.OnDateSetListener{
                    // 2 번재, 3번째, 3번째 파라미터 : 년, 월, 일
                    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                        textView.text = "${p1}년 ${p2+1}월 ${p3}일"
                    }
                }

                val pickerDialog = DatePickerDialog(this@MainActivity,datePicerListener,year,month,day)
                pickerDialog.show()

            }

            // 시간선택 다이얼로그
            button4.setOnClickListener {
                val calendar = Calendar.getInstance()

                val hour = calendar.get(Calendar.HOUR)
                val minute = calendar.get(Calendar.MINUTE)

                val timePickerListener = object : TimePickerDialog.OnTimeSetListener{
                    // 2 번째, 3 번째 : 시간, 분
                    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                        textView.text = "${p1}시 ${p2}분"
                    }
                }

                val pickerDialog = TimePickerDialog(this@MainActivity,timePickerListener,hour,minute,true)
                pickerDialog.show()
            }

            // 다이얼로그에 리스트 단일선택
            button5.setOnClickListener {
                val adapter = ArrayAdapter<String>(this@MainActivity,android.R.layout.simple_list_item_1, dateList)

                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("단일 선택 리스트 다이얼로그")
                builder.setIcon(R.mipmap.ic_launcher)

                // 어댑터 설정
                // 두번째 매개변수에는 사용자가 선택한 리스트 항목의 순서값
                builder.setAdapter(adapter){ dialogInterface: DialogInterface, i: Int ->
                    textView.text = "선택한 항목 : ${dateList[i]}"
                }

                builder.setNegativeButton("취소",null)
                builder.show()
            }

            // 다이얼로그에 리스트 다중선택
            button6.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("다중 선택 리스트 다이얼로그")
                builder.setIcon(R.mipmap.ic_launcher)

                // 데이트리스트 사이즈만큼 flase를 가지고 있는 array
                val boolArray = BooleanArray(dateList.size) { i -> false }

                // 원하는 항목을 미리 선택하게끔
                boolArray[0] = true
                boolArray[2] = true
                boolArray[4] = true

                // 항목이 선택되어있는지 boolean 값이 저장되어야 할 boolArray가 데이터만큼 있어야 한다
                builder.setMultiChoiceItems(dateList, boolArray, null)

                builder.setNegativeButton("취소",null)
                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                    textView.text = ""
                    // 다이얼로그에서 ListView 추출
                    val alertDialog = dialogInterface as AlertDialog
                    // 선택되어있는 정보를 추출
                    val position = alertDialog.listView.checkedItemPositions
                    // 선택이되어있거나 선택을했다가 다시 풀었던 항목의 최종값까지 나온다
                    // {순서값, 체크여부}
                    for (idx in 0 until position.size()){
                        // 현재 항목의 위치값 가져오기
                        val pos1 = position.keyAt(idx)
                        // 현재 항목 배열에 담기
                        boolArray[pos1] = position.get(pos1)
                    }
                    // 선택되어있는 항목만 출력
                    for(idx in 0 until boolArray.size){
                        if(boolArray[idx]){
                            textView.append("${dateList[idx]}\n")
                        }
                    }

                }

                builder.show()
            }


            // 다중선택 2
            button7.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("다중 선택 리스트 다이얼로그")
                builder.setIcon(R.mipmap.ic_launcher)

                // 마지막 매개변수에 체크 상태가 변경되었을 때 동작하는 리스너 설정
                // 여기에서 체크 상태가 변경된 항목의 체크 상태값을 BooleanArray에 담아준다
                // 두 번째 : 체크 상태가 변경된 항목의 순서값
                // 세 번째 : 체크 상태의 항목
                builder.setMultiChoiceItems(dateList, multiChoiceList){ dialogInterface: DialogInterface, i: Int, b: Boolean ->
                    multiChoiceList[i] = b
                }

                builder.setNegativeButton("취소",null)
                builder.setPositiveButton("확인",){ dialogInterface: DialogInterface, i: Int ->
                    textView.text = ""

                    for(idx in 0 until multiChoiceList.size){
                        if(multiChoiceList[idx]){
                            textView.append("${dateList[idx]}\n")
                        }
                    }
                }

                builder.show()
            }



        }
    }
}