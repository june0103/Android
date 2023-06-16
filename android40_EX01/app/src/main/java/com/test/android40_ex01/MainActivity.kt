package com.test.android40_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android40_ex01.databinding.ActivityMainBinding
import com.test.android40_ex01.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            inputView.visibility = View.GONE
            recyclerView.visibility = View.GONE

            //registerForContextMenu(activityMainBinding.recyclerView)

            etKorean.run {
                setOnEditorActionListener { v, actionId, event ->

                    val name = etName.text.toString()
                    val age = etAge.text.toString().toInt()
                    val korean = etKorean.text.toString().toInt()

                    val student = Student(name, age, korean)
                    studentList.add(student)

                    etName.setText("")
                    etAge.setText("")
                    etKorean.setText("")

                    etName.requestFocus()

                    false
                }
            }
            recyclerView.run {
                adapter = RecyclerAdapterClass()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        activityMainBinding.run {
            when (item.itemId) {
                R.id.menu_add -> {
                    inputView.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }

                R.id.menu_view -> {
                    inputView.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

//    override fun onCreateContextMenu(
//        menu: ContextMenu?,
//        v: View?,
//        menuInfo: ContextMenu.ContextMenuInfo?
//    ) {
//        super.onCreateContextMenu(menu, v, menuInfo)
//
//        if(v!=null){
//            when(v?.id){
//                R.id.recyclerView -> {
//                    val info = menuInfo as AdapterView.AdapterContextMenuInfo
//                    menu?.setHeaderTitle("${studentList[info.position].name}의 메뉴")
//                    menuInflater.inflate(R.menu.context_menu, menu)
//                }
//            }
//        }
//    }
//
//    override fun onContextItemSelected(item: MenuItem): Boolean {
//
//        when(item.itemId){
//            R.id.context_delete -> {
//                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
//                studentList.removeAt(info.position)
//
//                val adapter = activityMainBinding.recyclerView.adapter as RecyclerAdapterClass
//                adapter.notifyDataSetChanged()
//            }
//        }
//
//        return super.onContextItemSelected(item)
//    }

    inner class RecyclerAdapterClass :
        RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>() {

        inner class ViewHolderClass(rowBinding: RowBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {
            var tv_name: TextView
            var tv_age: TextView
            var tv_korean: TextView

            init {
                tv_name = rowBinding.tvNameRow
                tv_age = rowBinding.tvAgeRow
                tv_korean = rowBinding.tvKoreanRow

                // 항목 하나의 View에 컨텍스트 메뉴 생성 이벤트
                rowBinding.root.setOnCreateContextMenuListener { menu, v, menuInfo ->
                    menu.setHeaderTitle("${studentList[adapterPosition].name}")
                    menuInflater.inflate(R.menu.context_menu, menu)

                    // 첫 번째 메뉴에 대한 이벤트 처리, 두번째 메뉴는 menu[1] .... 증가
                    menu[0].setOnMenuItemClickListener {
                        // 현재 항목 번째를 삭제한다.
                        studentList.removeAt(adapterPosition)

                        this@RecyclerAdapterClass.notifyDataSetChanged()
                        false
                    }
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
            return studentList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.tv_name.text = "이름 : ${studentList[position].name}"
            holder.tv_age.text = "나이 : ${studentList[position].age}"
            holder.tv_korean.text = "국어점수 : ${studentList[position].korean}"


        }
    }


}

data class Student(var name: String, var age: Int, var korean: Int)