package com.test.android55_fragment

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.test.android55_fragment.databinding.FragmentMainBinding
import com.test.android55_fragment.databinding.RowBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity


    // Fragment가 보여줄 화면을 생성하는 메서드
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
            buttonMain1.setOnClickListener {
                // Fragment를 교체
                mainActivity.replaceFragment(FragmentName.FRAGMENT_INPUT, true, true)
            }

            recyclerView.run {
                adapter = RecyclerAdapterClass()
                layoutManager = LinearLayoutManager(mainActivity)
            }
        }

        return fragmentMainBinding.root
    }

    inner class RecyclerAdapterClass:RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>(){

        inner class ViewHolderClass(rowBinding: RowBinding):RecyclerView.ViewHolder(rowBinding.root){
            var textViewName : TextView
            var textViewAge : TextView
            var textViewKorean : TextView
            init {
                textViewName = rowBinding.textViewName
                textViewAge = rowBinding.textViewAge
                textViewKorean = rowBinding.textViewKorean

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
//            holder.textViewName.text = DataClass.studentList[position].name
//            holder.textViewAge.text = DataClass.studentList[position].age.toString()
//            holder.textViewKorean.text = DataClass.studentList[position].korean.toString()
            holder.textViewName.text = mainActivity.studentInfoList[position].name
            holder.textViewAge.text = mainActivity.studentInfoList[position].age.toString()
            holder.textViewKorean.text = mainActivity.studentInfoList[position].korean.toString()
        }


    }


}
