package com.test.android41_activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

// Activity가 실행 : onCreate -> onStart -> onResume
// 다른 Activity의 화면이 보일 때 : onPause -> Activity 일시 정지
// 다시 Activity의 화면이 보일 때 : onResume -> Activity 다시 동작
// Activity의 화면이 완전히 보이지 않게 될 때 : onPause -> onStop
// Activity의 화면이 다시 보여지게 될 때 : onRestart -> onstart -> onResume -> Activitiy 다시 동작
// Activity가 종료될 때 : onPause -> onStop -> onDestroy

// onCreate : Activity를 처음 실행하거나 화면 회전 발생에 대한 처리
// onPause : 일시정지 되기 전에 대한 처리
// onResume : 일시정지 되고 난 후 다시 돌아올 때의 처리
// onDestroy : Activity가 종료되기 전에 필요한 처리

class MainActivity : AppCompatActivity() {

    // Activity가 처음 실행 될 때 자동으로 호출
    // Activity가 관리할 View들을 생성하고 View들에 대한 초기작업을 수행
    // 화면 회전이 발생 했을 때의 처리도 수행
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("lion","onCreate")
    }

    // Activity가 처음 생성 될 때 onCreate 다음에 호출
    // Activity가 보이지 않는 상태가 되었다가 다시 보이게 될 때 onRestart 다음에 호출
    override fun onStart() {
        super.onStart()

        Log.d("lion","onStart")
    }

    // Activity가 처음 생성될 때 onStart 다음에 호출
    // 다른 Activity가 보여지고 다시 해당 Activity가 보여질 때 onStart 다음에 호출
    // Activity가 보여지지 않았다가 다시 보일 때 onStart 다음에 호출
    override fun onResume() {
        super.onResume()

        Log.d("lion","onResume")
    }

    // 다른 Activity가 눈에 보여질 때 호출
    // 현재 Activity는 일시 정지
    override fun onPause() {
        super.onPause()

        Log.d("lion", "onPause")
    }

    // 현재 Activity의 화면이 완전히 보이지 않게 될 때
    // onPause 다음에 호출
    override fun onStop() {
        super.onStop()

        Log.d("lion", "onStop")
    }

    // onStop이 호출된 이후 다시 Activity가 보여지는 상태가 될 때 호출
    override fun onRestart() {
        super.onRestart()

        Log.d("lion", "onRestart")
    }

    // Activity가 완전 종료 될 때 호출
    override fun onDestroy() {
        super.onDestroy()

        Log.d("lion", "onDestroy")
    }
}