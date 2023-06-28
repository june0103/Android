package com.test.android59_actionbarnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Homebutton을 활성화 한다.
        supportActionBar?.setHomeButtonEnabled(true)
        // HomeButton을 노출시킨다
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // 아이콘 변경(기본제공되는것을 사용하는게 좋다: 사용자는 익숙한것에 적응)
        // supportActionBar?.setHomeAsUpIndicator(R.mipmap.ic_launcher)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            // Back버튼
            android.R.id.home -> {
                // 현재 Activity종료
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}