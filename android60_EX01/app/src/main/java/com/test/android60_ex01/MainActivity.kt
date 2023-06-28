package com.test.android60_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android60_ex01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val studentInfoList = mutableListOf<StudentInfo>()
    // 터치한 항목의 순서값
    var rowPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // MainFragment를 보여준다
        replaceFragment(FragmentName.FRAGMENT_MAIN, false, false)

    }

    // 지정한 Fragment 를 보여주는 메서드
    fun replaceFragment(name: FragmentName, addToBackStack: Boolean, animate: Boolean) {
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 새로운 Fragment를 담을 변수
        var newFragment: Fragment? = null

        // 이름으로 분기한다.
        when (name) {
            FragmentName.FRAGMENT_MAIN -> {
                // Fragment 객체를 생성한다.
                newFragment = MainFragment()

            }

            FragmentName.FRAGMENT_INPUT -> {
                newFragment = InputFragment()

            }

            FragmentName.FRAGMENT_RESULT -> {
                newFragment = ResultFragment()
            }
        }


        if (newFragment != null) {
            // MainFragment가 보여지도록 Fragment를 교채한다.
            fragmentTransaction.replace(R.id.mainContainer, newFragment)

            if (animate == true) {
                // 애니메이션 설정
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            }

            if (addToBackStack == true) {
                // Fragment를 BackStack에 넣어 이전으로 돌아가는 기능이 동작 할 수 있도록
                fragmentTransaction.addToBackStack(name.str)
            }

            // 교체 명령이 동작하도록 한다.
            fragmentTransaction.commit()
        }
    }

    // Fragment를 BackStack에서 제거
    fun removeFragment(name: FragmentName){
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}

// Fragment 들을 구분하기 위한 값
enum class FragmentName(var str: String) {
    FRAGMENT_MAIN("MainFragment"),
    FRAGMENT_INPUT("InputFragment"),
    FRAGMENT_RESULT("ResultFragment")
}

data class StudentInfo(var name: String, var age: Int, var korean: Int)