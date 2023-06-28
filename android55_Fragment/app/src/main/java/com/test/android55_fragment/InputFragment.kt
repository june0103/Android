package com.test.android55_fragment

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.test.android55_fragment.databinding.FragmentInputBinding
import kotlin.concurrent.thread


class InputFragment : Fragment() {

    private lateinit var fragmentInputBinding: FragmentInputBinding
    private lateinit var mainActivity: MainActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity

        fragmentInputBinding = FragmentInputBinding.inflate(layoutInflater)

        fragmentInputBinding.editTextInputUserName.requestFocus()

        thread {
            SystemClock.sleep(500)
            val imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(fragmentInputBinding.editTextInputUserName, 0)
        }

        fragmentInputBinding.run {

            button.setOnClickListener {
                val name = editTextInputUserName.text.toString()
                val age = editTextInputUserAge.text.toString().toInt()
                val korean = editTextInputKorean.text.toString().toInt()

                val student = StudentClass(name, age, korean)
                DataClass.studentList.add(student)

                val studentInfo = StudentInfo(name, age, korean)
                mainActivity.studentInfoList.add(studentInfo)

                //mainActivity.replaceFragment(FragmentName.FRAGMENT_MAIN, true, true)
                mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)
            }
        }

        return fragmentInputBinding.root
    }

}