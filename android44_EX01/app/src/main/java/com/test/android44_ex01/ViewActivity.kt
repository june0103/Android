package com.test.android44_ex01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import com.test.android44_ex01.databinding.ActivityViewBinding

class ViewActivity : AppCompatActivity() {

    lateinit var activityViewBinding: ActivityViewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewBinding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(activityViewBinding.root)

        activityViewBinding.run {
//            val kind = intent.getStringExtra("kind").toString()
//            val num = intent.getIntExtra("num", 0).toString().toInt()
//            val country = intent.getStringExtra("country").toString()
//            tvKind.text = kind
//            tvNum.text = num.toString()
//            tvCountry.text = country

            val position = intent.getIntExtra("position",0)

            tvKind.text = DataClass.fruitList[position].kind
            tvNum.text = DataClass.fruitList[position].num.toString()
            tvCountry.text = DataClass.fruitList[position].country

            btTomain.run {
                setOnClickListener {
                    finish()
                }
            }
        }
    }
}