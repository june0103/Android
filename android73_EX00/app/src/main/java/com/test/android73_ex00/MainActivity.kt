package com.test.android73_ex00

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android73_ex00.databinding.ActivityMainBinding
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 리사이클러뷰 번호
    var rowPosition = 0

    companion object {
        // 프래그먼트들의 이름
        val MAIN_FRAGMENT = "MainFragment"
        val ADD_FRAGMENT = "AddFragment"
        val RESULT_FRAGMENT = "ResultFragment"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        replaceFragment(MAIN_FRAGMENT, false, false)
    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name: String, addToBackStack: Boolean, animate: Boolean) {
        // Fragment 교체 상태로 설정
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 새로운 Fragment를 담을 변수
        var newFragment = when (name) {

            MAIN_FRAGMENT -> {
                MainFragment()
            }

            ADD_FRAGMENT -> {
                AddFragment()
            }

            RESULT_FRAGMENT -> {
                ResultFragment()
            }


            else -> {
                Fragment()
            }

        }

        if (newFragment != null) {
            // Fragment를 교채
            fragmentTransaction.replace(R.id.mainContainer, newFragment)

            if (animate == true) {
                // 애니메이션을 설정
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            }

            if (addToBackStack == true) {
                // Fragment를 Backstack에 넣어 이전으로 돌아가는 기능이 동작
                fragmentTransaction.addToBackStack(name)
            }

            // 교체 명령 동작
            fragmentTransaction.commit()
        }
    }

    // Fragment를 BackStack에서 제거
    fun removeFragment(name: String) {
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

}

// 학생정보를 담을 객체
data class StudentClass(var idx: Int, var name: String, var age: Int, var korean: Int)