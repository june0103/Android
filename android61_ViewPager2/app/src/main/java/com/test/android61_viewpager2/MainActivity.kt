package com.test.android61_viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.test.android61_viewpager2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 사용할 Fragment
    val fragment1 = Sub1Fragment()
    val fragment2 = Sub2Fragment()
    val fragment3 = Sub3Fragment()
    val fragment4 = Sub4Fragment()
    val fragment5 = Sub5Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            pager2.run {
                adapter = MainFragmentStateAdapter(this@MainActivity)

                // 페이지 넘길 때 스크롤 방향을 설정(RTL 오른쪽으로) 기본은 LTR 왼쪽으로
                // layoutDirection = ViewPager2.LAYOUT_DIRECTION_RTL
                // 스크롤방향 방향 위아래
                // orientation = ViewPager2.ORIENTATION_VERTICAL

                // 페이지가 변경될때 반응
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){

                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        textViewMain.text = "page selected : $position"
                    }
                })


            }
        }

    }

    // ViewPager2 어댑터
    inner class MainFragmentStateAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
        // 보여줄 페이지수
        override fun getItemCount(): Int {
            return 5
        }

        // position 번째 Fragment를 반환하여 보여준다
        override fun createFragment(position: Int): Fragment {
            val resultFragment = when(position){
                0 -> fragment1
                1 -> fragment2
                2 -> fragment3
                3 -> fragment4
                4 -> fragment5
                else -> fragment1
            }
            return resultFragment
        }

    }


}