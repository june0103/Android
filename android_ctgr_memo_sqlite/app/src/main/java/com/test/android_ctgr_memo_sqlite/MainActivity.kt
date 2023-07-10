package com.test.android_ctgr_memo_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android_ctgr_memo_sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 리사이클러뷰 항목 번호
    var ctgrRowPosition = 0
    var memoRowPosition = 0

    companion object{
        // Activity가 관리할 프래그먼트들의 이름
        val PWSET_FRAGMENT = "PwSetFragment"
        val LOGIN_FRAGMENT = "LoginFragment"
        val MAIN_FRAGMENT = "MainFragment"
        val MEMOLIST_FRAGMENT = "MemoListFragment"
        val ADD_FRAGMENT = "MemoAddFragment"
        val RESULT_FRAGMENT = "MemoResultFragment"
        val MODIFY_FRAGMENT = "MemoModifyFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 비밀번호가 설정 여부에 따라 보여지는 첫 화면
        if(pwSet()){
            replaceFragment(LOGIN_FRAGMENT,false,false)
        }else{
            replaceFragment(PWSET_FRAGMENT,false,false)
        }
    }

    // 비밀번호 설정 여부 검사
    fun pwSet():Boolean{
        val password = DAO.getPw(this@MainActivity)
        return password != null
    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name:String, addToBackStack:Boolean, animate:Boolean){
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 새로운 Fragment를 담을 변수
        var newFragment = when(name){

            // 카테고리 리스트
            MAIN_FRAGMENT -> {
                MainFragment()
            }
            // 메모 리스트
            MEMOLIST_FRAGMENT -> {
                MemoListFragment()
            }
            // 메모 추가
            ADD_FRAGMENT -> {
                MemoAddFragment()
            }
            // 메모 보기
            RESULT_FRAGMENT -> {
                MemoResultFragment()
            }
            // 메모 수정
            MODIFY_FRAGMENT -> {
                MemoModifyFragment()
            }
            // 비밀번호 설정
            PWSET_FRAGMENT -> {
                PwSetFragment()
            }
            // 로그인
            LOGIN_FRAGMENT -> {
                LoginFragment()
            }
            else -> {
                Fragment()
            }
        }

        if(newFragment != null) {
            // Fragment를 교채
            fragmentTransaction.replace(R.id.mainContainer, newFragment)

            if (animate == true) {
                // 애니메이션을 설정
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            }

            if (addToBackStack == true) {
                // Fragment를 Backstack에 넣어 이전으로 돌아가는 기능이 동작할 수 있도록 한다.
                fragmentTransaction.addToBackStack(name)
            }

            // 교체 명령이 동작하도록 한다.
            fragmentTransaction.commit()
        }
    }

    // Fragment를 BackStack에서 제거한다.
    fun removeFragment(name:String){
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}

// 카테고리 객체
data class CtgrClass(var idx : Int,var title:String)
// 메모 객체
data class MemoClass(var idx : Int,var title:String, var content:String, var memodate:String, var categoryIdx:Int)