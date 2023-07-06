package com.test.android73_ex00

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android73_ex00.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    lateinit var fragmentAddBinding : FragmentAddBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentAddBinding = FragmentAddBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentAddBinding.run {
            editTextAddKorean.setOnEditorActionListener{ textView, i, keyEvent  ->
                val name = editTextAddName.text.toString()
                val age = editTextAddAge.text.toString().toInt()
                val korean = editTextAddKorean.text.toString().toInt()

                val student = StudentClass(0,name, age, korean)
                // DAO를 통해 데이터 추가
                DAO.insertData(requireContext(), student)

                mainActivity.removeFragment(MainActivity.ADD_FRAGMENT)

                false
            }
        }

        return fragmentAddBinding.root
    }

}