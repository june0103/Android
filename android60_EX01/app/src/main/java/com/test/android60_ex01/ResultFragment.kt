package com.test.android60_ex01

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android60_ex01.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    lateinit var fragmentResultBinding: FragmentResultBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mainActivity = activity as MainActivity
        fragmentResultBinding = FragmentResultBinding.inflate(layoutInflater)

        fragmentResultBinding.run {

            toolbar3.run {
                title = "${mainActivity.studentInfoList[mainActivity.rowPosition].name} 정보"
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
                    mainActivity.removeFragment(FragmentName.FRAGMENT_RESULT)
                }
            }

            // 데이터 추출
            val (name, age, korean) = mainActivity.studentInfoList[mainActivity.rowPosition]

            textViewResultName.text = "이름 : $name"
            textViewResultAge.text = "나이 : $age"
            textViewResultKorean.text = "국어점수 : $korean"
        }

        return fragmentResultBinding.root
    }

}