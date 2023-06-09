package com.test.android03_viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import com.test.android03_viewbinding.databinding.ActivityMainBinding
import com.test.android03_viewbinding.databinding.ActivityTestBinding

class MainActivity : AppCompatActivity() {

    // ViewBinding
    // res/layout 폴더에 있는 xml 파일 하나달 하나의 클래스가 만들어진다
    // 이 클래스에는 xml 파일이 가지고 있는 view들을 관리한느 기능이 들어가 있다
    // 이를 통해 개발자가 view를 직접 추출하지 않고 사용할 수 있다
    // 안드로이드 OS가 알아서 view를 추출하여 변수에 담아준다

    //

    // 1. app 수준의 build.gradle 파일에 다음의 코드를 추가
    // viewBinding {
    //       enable = true
    // }

    // 생성된viewbinding 객체에는 view의 id와 동일한 이름의 변수가 만들어지고 그 변수에는 view 객체가 들어있다


    // view를 담을 변수
//    lateinit var textView: TextView
//    lateinit var button: Button
//    lateinit var button2: Button
//    lateinit var button3: Button
//    lateinit var button4: Button

    // ViewBinding 객체를 담을 변수
    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var activityTestBinding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        // viewBinding 객체를 가져온다
        // layoutInflater : XML 파일을 통해 객체를 생성하는 도구
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        activityTestBinding = ActivityTestBinding.inflate(layoutInflater)

        // viewbinding 객체가 관리하는 view 중에 최상위 view를 지정하여 화면에 나타나게한다
        setContentView(activityMainBinding.root)
        //setContentView(activityTestBinding.root)

        // view  객체를 가져온다
//        textView = findViewById(R.id.textView)
//        button = findViewById(R.id.button)
//        button2 = findViewById(R.id.button2)
//        button3 = findViewById(R.id.button3)
//        button4 = findViewById(R.id.button4)
        
        // 리스너 설정
        val button1ClickListener = Button1ClickListener()
        val button2ClickListener = Button2ClickListener()
        val button3ClickListener = Button3ClickListener()
        val button4ClickListener = Button4ClickListener()

//        button.setOnClickListener(button1ClickListener)
//        button2.setOnClickListener(button2ClickListener)
//        button3.setOnClickListener(button3ClickListener)
//        button4.setOnClickListener(button4ClickListener)
//        activityMainBinding.button.setOnClickListener(button1ClickListener)
//        activityMainBinding.button2.setOnClickListener(button2ClickListener)
//        activityMainBinding.button3.setOnClickListener(button3ClickListener)
//        activityMainBinding.button4.setOnClickListener(button4ClickListener)

        // 오버라이딩 하는 함수가 하나만 있는경우 람다식과 같은 방법도 제공한다
//        activityMainBinding.button.setOnClickListener {
//            activityMainBinding.textView.text = "10 + 10 = ${10 + 10}"
//        }
//        activityMainBinding.button2.setOnClickListener {
//            activityMainBinding.textView.text = "10 - 10 = ${10 - 10}"
//        }
//        activityMainBinding.button3.setOnClickListener {
//            activityMainBinding.textView.text = "10 * 10 = ${10 * 10}"
//        }
//        activityMainBinding.button4.setOnClickListener {
//            activityMainBinding.textView.text = "10 / 10 = ${10 / 10}"
//        }

        activityMainBinding.run {
            button.run {
                setOnClickListener {
                    activityMainBinding.textView.text = "10 + 10 = ${10 + 10}"
                }
            }
            button2.run {
                setOnClickListener {
                    activityMainBinding.textView.text = "10 - 10 = ${10 - 10}"
                }
            }
            button3.run {
                setOnClickListener {
                    activityMainBinding.textView.text = "10 * 10 = ${10 * 10}"
                }
            }
            button4.run {
                setOnClickListener {
                    activityMainBinding.textView.text = "10 / 10 = ${10 / 10}"
                }
            }
        }

    }

    // 각 버튼별 리스너
    inner class Button1ClickListener : OnClickListener{
        override fun onClick(p0: View?) {
//            textView.text = "10 + 10 = ${10 + 10}"
            activityMainBinding.textView.text = "10 + 10 = ${10 + 10}"
        }
    }
    inner class Button2ClickListener : OnClickListener{
        override fun onClick(p0: View?) {
//            textView.text = "10 - 10 = ${10 - 10}"
            activityMainBinding.textView.text = "10 - 10 = ${10 - 10}"
        }
    }
    inner class Button3ClickListener : OnClickListener{
        override fun onClick(p0: View?) {
//            textView.text = "10 * 10 = ${10 * 10}"
            activityMainBinding.textView.text = "10 * 10 = ${10 * 10}"
        }
    }
    inner class Button4ClickListener : OnClickListener{
        override fun onClick(p0: View?) {
//            textView.text = "10 / 10 = ${10 / 10}"
            activityMainBinding.textView.text = "10 / 10 = ${10 / 10}"
        }
    }

}