package com.test.android60_ex01

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.test.android60_ex01.databinding.FragmentInputBinding
import kotlin.concurrent.thread


class InputFragment : Fragment() {

    private lateinit var fragmentInputBinding: FragmentInputBinding
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mainActivity = activity as MainActivity

        fragmentInputBinding = FragmentInputBinding.inflate(layoutInflater)

        fragmentInputBinding.editTextInputUserName.requestFocus()

        thread {
            SystemClock.sleep(500)
            val imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(fragmentInputBinding.editTextInputUserName, 0)
        }

        fragmentInputBinding.run {

            toolbar2.run {
                title = "학생정보 입력`"
                setTitleTextColor(Color.WHITE)
                // 백 버튼 아이콘을 표시한다.
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                // 백 버튼 아이콘 색상
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                    // BlendModeColorFilter(투명색이 아닌부분을 흰색으로)
                    navigationIcon?.colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
                } else{
                    navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                }

                // 백 버튼을 누르면 동작하는 리스너
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)
                }
            }

            editTextInputKorean.run {
                setOnEditorActionListener { textView, i, keyEvent ->
                    val name = editTextInputUserName.text.toString()
                    val age = editTextInputUserAge.text.toString().toInt()
                    val korean = editTextInputKorean.text.toString().toInt()

                    val studentInfo = StudentInfo(name, age, korean)
                    mainActivity.studentInfoList.add(studentInfo)

                    //mainActivity.replaceFragment(FragmentName.FRAGMENT_MAIN, true, true)

                    editTextInputUserName.setText("")
                    editTextInputUserAge.setText("")
                    editTextInputKorean.setText("")
                    editTextInputUserName.requestFocus()

                    false
                }

            }
        }

        return fragmentInputBinding.root
    }


}