package com.test.android45_app2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.test.android45_app2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val c1 = ActivityResultContracts.StartActivityForResult()
        val activityLauncher = registerForActivityResult(c1){
            val value1 = it.data?.getIntExtra("value1",0)
            val value2 = it.data?.getStringExtra("value2")

            activityMainBinding.run {
                textView.text = "value1 : ${value1}\n"
                textView.append("value2 : $value2")
            }
        }

        activityMainBinding.run {



            button.run {
                setOnClickListener {
                    // 다른 앱의 Activity에 붙혀준 이름(manifestes의 Intent Filter Action name)을 지정하여 Intent 생성
                    // exported가 false가 아니라 true여야 한다
                    val newIntent = Intent("com.test.android45_third_activity")
                    newIntent.putExtra("data1",100)
                    newIntent.putExtra("data2","안녕하세요")

//                    startActivity(newIntent)
                    activityLauncher.launch(newIntent)
                }
            }

            buttonShowMap.run {
                setOnClickListener {

                    // " " 정해진 액티비티의이름을 적는다
                    // 위도와 경도를 문자열로 만들어준다 (지도)
                    // val address = "geo:37.243243,131.861601"
                    // val uri = Uri.parse(address)
                    // val newIntent = Intent(Intent.ACTION_VIEW,uri)

                    // 웹사이트
                    val address = "http://developer.android.com"
                    val uri = Uri.parse(address)
                    val newIntent = Intent(Intent.ACTION_VIEW,uri)
                    startActivity(newIntent)
                }
            }


        }
    }
}