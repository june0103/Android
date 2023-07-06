package com.test.android_memo_sqlite

import android.app.AlertDialog
import android.content.DialogInterface
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
import com.test.android_memo_sqlite.databinding.FragmentModifyBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class ModifyFragment : Fragment() {

    lateinit var fragmentModifyBinding: FragmentModifyBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentModifyBinding = FragmentModifyBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        val memo = DAO.selectData(requireContext(),mainActivity.rowPosition)

        fragmentModifyBinding.run {

            etModifyTitle.setText(memo.title)
            etModifyContent.setText(memo.content)

            toolbarModify.run {
                title = "메모 수정"
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
                    memo.title = etModifyTitle.text.toString()
                    memo.content = etModifyContent.text.toString()
                    memo.memodate = memo.memodate

                    DAO.updateData(requireContext(),memo)

                    mainActivity.removeFragment(MainActivity.MODIFY_FRAGMENT)
                    false
                }
                // 백 버튼을 누르면 동작하는 리스너
                setNavigationOnClickListener {

                    mainActivity.removeFragment(MainActivity.MODIFY_FRAGMENT)
                }
            }
        }

        return fragmentModifyBinding.root
    }
}