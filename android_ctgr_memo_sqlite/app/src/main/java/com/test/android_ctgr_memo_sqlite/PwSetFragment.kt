package com.test.android_ctgr_memo_sqlite

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.test.android_ctgr_memo_sqlite.databinding.FragmentPwSetBinding


class PwSetFragment : Fragment() {

    lateinit var fragmentPwSetBinding: FragmentPwSetBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentPwSetBinding = FragmentPwSetBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentPwSetBinding.run {
            toolbarPwset.run {
                title = "비밀번호 설정"
                setTitleTextColor(Color.WHITE)
            }
            btPwsetOk.setOnClickListener {
                val setPw = etSetpw.text.toString()
                val setPwConfirm = etSetpwconfirm.text.toString()
                if(setPw == setPwConfirm){
                    DAO.setPw(requireContext(),setPwConfirm)
                    mainActivity.replaceFragment(MainActivity.LOGIN_FRAGMENT, false, true)
                }else{
                    etSetpwconfirm.setText("")
                    etSetpwconfirm.requestFocus()
                    Toast.makeText(requireContext(),"비밀번호를 다시 확인해 주세요",Toast.LENGTH_SHORT).show()
                }
            }

        }
        // Inflate the layout for this fragment
        return fragmentPwSetBinding.root
    }
}