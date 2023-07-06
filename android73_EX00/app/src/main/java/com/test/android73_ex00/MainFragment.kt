package com.test.android73_ex00

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android73_ex00.databinding.FragmentMainBinding
import com.test.android73_ex00.databinding.RowMainBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding : FragmentMainBinding
    lateinit var mainActivity: MainActivity

    // 학생의 정보를 담을 리스트
    var studentList = mutableListOf<StudentClass>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentMainBinding.run {
            toolbarMain.run {
                title = "MainFragment"
                inflateMenu(R.menu.main_menu)
                setOnMenuItemClickListener {
                    mainActivity.replaceFragment(MainActivity.ADD_FRAGMENT, true, true)
                    false
                }
            }

            recyclerViewMain.run {
                adapter = MainRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                addItemDecoration(DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL))
            }
        }

        // Inflate the layout for this fragment
        return fragmentMainBinding.root
    }


    inner class MainRecyclerViewAdapter :
        RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolderClass>() {

        inner class MainViewHolderClass(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            var textViewRowMainName : TextView

            init{
                textViewRowMainName = rowMainBinding.textViewMainRowName

                rowMainBinding.root.setOnClickListener {
                    val student = studentList[adapterPosition]
                    val studentidx = student.idx
                    mainActivity.rowPosition = studentidx
                    mainActivity.replaceFragment(MainActivity.RESULT_FRAGMENT, true, true)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolderClass {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val mainViewHolderClass = MainViewHolderClass(rowMainBinding)

            rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return mainViewHolderClass
        }

        override fun getItemCount(): Int {
            return studentList.size
        }

        override fun onBindViewHolder(holder: MainViewHolderClass, position: Int) {
            holder.textViewRowMainName.text = studentList[position].name
        }
    }

    override fun onResume() {
        super.onResume()
        // 데이터 불러온다
        studentList = DAO.selectAllData(requireContext())

        // 리사이클러뷰 갱신
        fragmentMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
    }
}