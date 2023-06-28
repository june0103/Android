package com.test.android60_ex01

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android60_ex01.databinding.FragmentMainBinding
import com.test.android60_ex01.databinding.RowBinding

class MainFragment : Fragment() {


    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // Fragment를 관리하는 Activity객체를 가져온다
        mainActivity = activity as MainActivity
        // ViewBinding
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)

        fragmentMainBinding.run {
            toolbar.run {
                title = "학생정보"
                // 글자색상
                setTitleTextColor(Color.WHITE)
                //  옵션메뉴 구성
                inflateMenu(R.menu.main_menu)
                // 상단 메뉴를 눌러주면 동작하는 리스너
                setOnMenuItemClickListener {
                    // 사용자가 누른 메뉴의 id로 분기한다.
                    when (it?.itemId) {
                        R.id.item_add -> {
                            // Fragment를 교체
                            mainActivity.replaceFragment(FragmentName.FRAGMENT_INPUT, true, true)
                        }
                    }
                    false
                }
            }

            recyclerView.run {
                adapter = RecyclerAdapterClass()
                layoutManager = LinearLayoutManager(mainActivity)
            }
        }

        return fragmentMainBinding.root
    }


    inner class RecyclerAdapterClass: RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>(){

        inner class ViewHolderClass(rowBinding: RowBinding): RecyclerView.ViewHolder(rowBinding.root){
            var textViewName : TextView

            init {
                textViewName = rowBinding.textViewName

                rowBinding.root.setOnClickListener {
                    // 터치한 항목의 위치값을 변수에 담아준다
                    mainActivity.rowPosition = adapterPosition
                    // ResulteFragment로 변경
                    mainActivity.replaceFragment(FragmentName.FRAGMENT_RESULT, true, true)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            rowBinding.root.layoutParams = params

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            // return DataClass.studentList.size
            return mainActivity.studentInfoList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {

            holder.textViewName.text = mainActivity.studentInfoList[position].name

        }


    }

}