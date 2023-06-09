package com.test.android13_edittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import com.test.android13_edittext.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            // 현재 포커스를 가지고있는 view와 연결된 키보드를 올라오게한다
            // onCreate 메서드가 끝나야 화면이 나타나는데 그 전에 키보드가 올라오게 하는 명령을 전달하기 때문에 무시된다
            // 쓰레드로 운영해야한다
            thread {
                // onCreate 메서드의 수행이 끝날 때 까지 대기
                SystemClock.sleep(500)

                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(currentFocus,0)
            }
            editTextText.run {
                // EditText에 새로운 문자열을 설정
                setText("코드에서 문자열 설정")
                // 포커스를 준다
                requestFocus()

                // Enter를 눌렀을 때 이벤트
                setOnEditorActionListener{
                    v, actionid, event ->
                    textView.text = "엔터 버튼을 눌렀습니다"
                    textView2.text = text.toString()
                    // true를 반환하면 Enter를 누른 후에 포커스가 현제 EditText로 유지
                    // false를 반환하면 Enter를 누른 후에 포커스가 다음 EditText로 이동
                    true
                }

                // 입력 감시자 설정
                val editTextWatcher1 = EditTextWatcher1()
                addTextChangedListener(editTextWatcher1)

            }

            editTextTextPassword.run{
                // 고차함수는 오버라이딩할 메소드가 한개일때만 사용가능하지만 가장 많이 사용하고 중요한 역할을 수행하는 메서드가 있다면 고차함수를 지원한다
                // addTextChangedListener를 사용할 때 고차함수를 사용하면 TextWatcher의 after 역할을 수행
                // 사용자의 입력 내용을 받아 낼 수 있다
                addTextChangedListener {
                    textView.text = it
                }
            }

            button.run {
                setOnClickListener {
                    // EditText의 문자열을 가지고 온다
                    val str1 = editTextText.text.toString()
                    textView.text = str1

                    // 키보드를 내린다
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    if(currentFocus != null){
                        // currentFocus : 현재 포커스를 가지고 있는 View를 지칭
                        imm.hideSoftInputFromWindow(currentFocus!!.windowToken,0)

                        // 포커스 해제
                        currentFocus!!.clearFocus()
                    }
                }
            }
        }
    }
    // EditText 입력 감시자 (입력 할 때 마다 감시)
    inner class EditTextWatcher1 : TextWatcher {
        // 입력 내용 변경 전
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            activityMainBinding.textView.text = "before : ${s}"
        }

        // 입력 내용 변경 했을 때
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            activityMainBinding.textView2.text = "changed : ${s}"
        }

        // 입력 내용이 변경된 후
        override fun afterTextChanged(s: Editable?) {
            activityMainBinding.textView3.text = "after : ${s}"
        }

    }
}