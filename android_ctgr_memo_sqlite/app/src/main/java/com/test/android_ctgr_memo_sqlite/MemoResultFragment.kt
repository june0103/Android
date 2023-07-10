package com.test.android_ctgr_memo_sqlite

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
import com.test.android_ctgr_memo_sqlite.databinding.FragmentMemoResultBinding


class MemoResultFragment : Fragment() {
    lateinit var fragmentMemoResultBinding: FragmentMemoResultBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMemoResultBinding = FragmentMemoResultBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        val memo = DAO.selectData(requireContext(),mainActivity.memoRowPosition)

        fragmentMemoResultBinding.run{

            tvResultTitle.text = memo.title
            tvResultDate.text = memo.memodate
            tvResultContent.text = memo.content

            toolbarResult.run {
                title = "메모 보기"
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

                inflateMenu(R.menu.result_menu)
                setOnMenuItemClickListener {
                    when(it?.itemId){
                        R.id.menuResultModify -> {
                            mainActivity.replaceFragment(MainActivity.MODIFY_FRAGMENT, true, true)
                        }
                        R.id.menuResultDelete -> {
                            // 다이얼로그 생성을 위한 객체 생성
                            val builder = AlertDialog.Builder(requireContext())

                            // 타이틀
                            builder.setTitle("메모 삭제")
                            // 메시지
                            builder.setMessage("메모를 삭제 하겠습니까?")
                            builder.setNegativeButton("취소",null)
                            builder.setPositiveButton("삭제"){ dialogInterface: DialogInterface, i: Int ->
                                DAO.deleteData(requireContext(),mainActivity.memoRowPosition)
                                mainActivity.removeFragment(MainActivity.RESULT_FRAGMENT)
                            }
                            builder.show()
                        }
                    }
                    false
                }
                // 백 버튼을 누르면 동작하는 리스너
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.RESULT_FRAGMENT)
                }
            }
        }

        return fragmentMemoResultBinding.root
    }
}