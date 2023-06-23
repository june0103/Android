package com.test.android46_toast

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.test.android46_toast.databinding.ActivityMainBinding
import com.test.android46_toast.databinding.SnackbarBinding
import com.test.android46_toast.databinding.ToastBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var snackBar1 : Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.run {
                setOnClickListener {
                    val t1 = Toast.makeText(this@MainActivity,"기본 Toast입니다",Toast.LENGTH_SHORT)
                    t1.show()

                    // 메시지가 사라질 때 동작할 코드
                    // Android 11부터 가능
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                        t1.addCallback(object : Toast.Callback(){
                            // Toast 메시지가 나타날때
                            override fun onToastShown() {
                                super.onToastShown()

                                textView.text = "토스트메세지 실행"
                            }

                            // TOast 메시지가 사라질 때
                            override fun onToastHidden() {
                                super.onToastHidden()

                                textView.text = "토스트메세지 종료"
                            }
                        })
                    }
                }

            }

            button2.run {
                setOnClickListener {
                    // toast 커스텀
                    // ViewBinding 객체 생성
                    val toastBinding = ToastBinding.inflate(layoutInflater)

                    toastBinding.run {
                        imageViewToast.setImageResource(R.drawable.img_android)
                        textViewToast.text = "Custom Toast"

                        val t2 = Toast(this@MainActivity)
                        // view 설정
                        t2.view = toastBinding.root

                        // 배경
                        toastBinding.root.setBackgroundResource(android.R.drawable.toast_frame)
                        // textView 글자 색상
                        textViewToast.setTextColor(Color.WHITE)

                        // 위치설정
                        // 중앙에서부터 아래로 (y축) 300이동된 부분
                        t2.setGravity(Gravity.CENTER,0,300)

                        // 시간설정
                        t2.duration = Toast.LENGTH_LONG
                        t2.show()
                    }
                }
            }

            button3.run {
                setOnClickListener {
                    // snackBar 객체 생성
                    // snackBar1 = Snackbar.make(it,"기본 SnackBar",Snackbar.LENGTH_SHORT)
                    // snackBar1 = Snackbar.make(it,"기본 SnackBar",Snackbar.LENGTH_LONG)
                    snackBar1 = Snackbar.make(it,"기본 SnackBar",Snackbar.LENGTH_INDEFINITE)

                    // SnackBar의 Callback
                    snackBar1.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>(){
                        // 나타날때
                        override fun onShown(transientBottomBar: Snackbar?) {
                            super.onShown(transientBottomBar)
                            textView.text = "SnackBar 나타남"
                        }

                        // 사라질때
                        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                            super.onDismissed(transientBottomBar, event)
                            textView.text = "SnackBar 사라짐"
                        }
                    })

                    // 메시지 색상 변경
                    snackBar1.setTextColor(Color.RED)
                    // 배경색
                    snackBar1.setBackgroundTint(Color.BLUE)
                    // 애니메이션
                    // snackBar1.animationMode = Snackbar.ANIMATION_MODE_FADE
                    snackBar1.animationMode = Snackbar.ANIMATION_MODE_SLIDE

                    // Action은 하나만 설정가능 (보여지는게 하나다)
                    snackBar1.setAction("Action1"){
                        textView2.text = "Action1을 눌렀습니다"
                    }



                    snackBar1.show()
                }
            }

            button4.run {
                setOnClickListener {
                    // snackBar1변수가 초기화 되어있다면
                    if(::snackBar1.isInitialized){
                        // 현재 스낵바가 보여지고 있는 상태라면
                        if(snackBar1.isShown){
                            // 스낵바를 사라지게 한다
                            snackBar1.dismiss()
                        }
                    }
                }
            }

            button5.run {
                setOnClickListener {
                    // snackBar 커스텀
                    // snackbar 생성
                    val snackBar2 = Snackbar.make(it,"Custem SnackBar",Snackbar.LENGTH_SHORT)
                    // ViewBinding
                    val snackbarBinding = SnackbarBinding.inflate(layoutInflater)

                    // View설정
                    snackbarBinding.run {
                        imageViewSnackBar.setImageResource(R.drawable.img_android)
                        textViewSnackBar.text = "새로 추가된 View"
                        textViewSnackBar.setTextColor(Color.WHITE)
                    }

                    // SnackBar의 Layout을 추출하여 새로운 뷰를 추가
                    val snackBarLayout = snackBar2.view as Snackbar.SnackbarLayout
                    snackBarLayout.addView(snackbarBinding.root)

                    // snackbar가 가지고있는 TextView를 가린다
                    val t1 = snackBarLayout.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                    t1.visibility = View.INVISIBLE

                    snackBar2.show()
                }
            }
        }

    }
}