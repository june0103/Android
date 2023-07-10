package com.test.android_ctgr_memo_sqlite

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.test.android_ctgr_memo_sqlite.databinding.FragmentLoginBinding
import com.test.android_ctgr_memo_sqlite.databinding.FragmentMainBinding
import com.test.android_ctgr_memo_sqlite.databinding.FragmentMemoAddBinding


class LoginFragment : Fragment() {

    lateinit var fragmentLoginBinding: FragmentLoginBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentLoginBinding.run {
            toolbarLogin.run {
                title = "로그인"
                setTitleTextColor(Color.WHITE)
            }
            btLoginOk.setOnClickListener {
                // 입력받은 비밀번호
                val inputPw = etLoginPw.text.toString()
                // DB에 저장된 비밀번호
                val getPw = DAO.getPw(requireContext())

                if(inputPw == getPw){
                    Toast.makeText(requireContext(),"로그인 성공",Toast.LENGTH_SHORT).show()
                    mainActivity.replaceFragment(MainActivity.MAIN_FRAGMENT, false, true)
                }else{
                    etLoginPw.setText("")
                    etLoginPw.requestFocus()
                    Toast.makeText(requireContext(),"비밀번호를 다시 입력해 주세요",Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Inflate the layout for this fragment
        return fragmentLoginBinding.root
    }

}