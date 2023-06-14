package com.test.android36_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android36_ex01.databinding.ActivityMainBinding
import com.test.android36_ex01.databinding.RowBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    val studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            recyclerView.run {
                adapter = RecyclerAdapterClass()
                layoutManager= LinearLayoutManager(this@MainActivity)
            }

            etKorean.run {
                setOnEditorActionListener { textView, i, keyEvent ->
                    val name = etName.text.toString()
                    val age = etAge.text.toString().toInt()
                    val korean = text.toString().toInt()
                    val student = Student(name, age, korean)
                    studentList.add(student)

                    val adapter = recyclerView.adapter as RecyclerAdapterClass
                    adapter.notifyDataSetChanged()

                    etName.setText("")
                    etAge.setText("")
                    setText("")
                    false
                }
            }
        }
    }

    inner class RecyclerAdapterClass:RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>(){

        inner class ViewHolderClass(rowBinding: RowBinding) :ViewHolder(rowBinding.root){

            var tv_nameRow : TextView
            var tv_ageRow : TextView
            var tv_koreanRow : TextView

            init {
                tv_nameRow = rowBinding.tvNameRow
                tv_ageRow = rowBinding.tvAgeRow
                tv_koreanRow = rowBinding.tvKoreanRow
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return studentList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.tv_nameRow.text = studentList[position].name
            holder.tv_ageRow.text = studentList[position].age.toString()
            holder.tv_koreanRow.text = "국어 점수 : ${studentList[position].korean.toString()}"
        }
    }
}

data class Student(var name:String,var age:Int,var korean:Int)