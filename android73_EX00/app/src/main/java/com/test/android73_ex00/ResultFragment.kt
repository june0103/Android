package com.test.android73_ex00

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android73_ex00.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    lateinit var fragmentResultBinding: FragmentResultBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentResultBinding = FragmentResultBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        val student = DAO.selectData(requireContext(),mainActivity.rowPosition)

        // 선택한 행 번째의 객체에서 데이터를 가져와 출력한다.
        fragmentResultBinding.run{
            textViewResult1.text = "이름 : ${student.name}"
            textViewResult2.text = "나이 : ${student.age.toString()}"
            textViewResult3.text = "국어점수 : ${student.korean.toString()}"
        }

        return fragmentResultBinding.root
    }

}