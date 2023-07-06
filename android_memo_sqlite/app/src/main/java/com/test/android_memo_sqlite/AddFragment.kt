package com.test.android_memo_sqlite

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
import com.test.android_memo_sqlite.databinding.FragmentAddBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
            toolbarAdd.run {
                title = "메모 추가"
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

                inflateMenu(R.menu.add_menu)
                setOnMenuItemClickListener {
                    val title = etAddTitle.text.toString()
                    val content = etAddContent.text.toString()
                    // 현재 시간을 구해 년 - 월 - 일 양식의 문자열을 만들어준다
                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val memodate = sdf.format(Date())

                    val memo = MemoClass(0,title,content,memodate)
                    DAO.insertData(requireContext(),memo)

                    mainActivity.removeFragment(MainActivity.ADD_FRAGMENT)
                    false
                }
                // 백 버튼을 누르면 동작하는 리스너
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.ADD_FRAGMENT)
                }
            }
        }

        return fragmentAddBinding.root
    }

}