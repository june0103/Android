package com.test.android74_memoapp

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.test.android74_memoapp.databinding.FragmentMemoModifyBinding


class MemoModifyFragment : Fragment() {

    lateinit var fragmentMemoModifyBinding:FragmentMemoModifyBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentMemoModifyBinding = FragmentMemoModifyBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        // 메모 데이터를 가져온다
        val memoIdx = arguments?.getInt("memo_idx")
        val memoClass = MemoDAO.selectOne(mainActivity, memoIdx!!)

        fragmentMemoModifyBinding.run{

            // EditText에 메모 내용을 체운다
            editTextModifySubject.setText(memoClass?.memoSubject)
            editTextModifyText.setText(memoClass?.memoText)

            toolbarMemoModify.run{
                title = "메모 수정"
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.MEMO_MODIFY_FRAGMENT)
                }

                inflateMenu(R.menu.memo_modify_menu)
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.memo_modify_menu_item1 -> {

                            // 입력한 내용을 가져온다
                            val str1 = editTextModifySubject.text.toString()
                            val str2 = editTextModifyText.text.toString()

                            // 유효성 검사
                            if(str1.length == 0){
                                val builder = AlertDialog.Builder(mainActivity)
                                builder.setTitle("제목 입력 오류")
                                builder.setMessage("제목을 입력해주세요")
                                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                                    mainActivity.showSoftInput(editTextModifySubject, 200)
                                }
                                builder.show()
                                return@setOnMenuItemClickListener false
                            }

                            if(str2.length == 0){
                                val builder = AlertDialog.Builder(mainActivity)
                                builder.setTitle("내용 입력 오류")
                                builder.setMessage("내용을 입력해주세요")
                                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                                    mainActivity.showSoftInput(editTextModifyText, 200)
                                }
                                builder.show()
                                return@setOnMenuItemClickListener false
                            }

                            // 새로운 내용을 객체에 담아준다.
                            memoClass?.memoSubject = str1
                            memoClass?.memoText = str2
                            // 갱신한다.
                            MemoDAO.update(mainActivity, memoClass!!)

                            mainActivity.removeFragment(MainActivity.MEMO_MODIFY_FRAGMENT)
                        }
                    }
                    false
                }
            }
        }

        return fragmentMemoModifyBinding.root
    }

}