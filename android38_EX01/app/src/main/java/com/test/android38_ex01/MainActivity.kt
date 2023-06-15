package com.test.android38_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android38_ex01.databinding.ActivityMainBinding
import com.test.android38_ex01.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    val studentList = mutableListOf<Student>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            btInputView.run {
                setOnClickListener{
                    etName.visibility = View.VISIBLE
                    etAge.visibility = View.VISIBLE
                    etKorean.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    etName.requestFocus()
                }
            }
            btRecyclerView.run {
                setOnClickListener {

                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(currentFocus!!.windowToken,0)
                    currentFocus!!.clearFocus()

                    etName.visibility = View.GONE
                    etAge.visibility = View.GONE
                    etKorean.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                }
            }

            recyclerView.run {
                adapter = RecyclerAdapterClass()
                layoutManager= LinearLayoutManager(this@MainActivity)
            }
            etName.run {
                requestFocus()
            }

            etKorean.run {
                setOnEditorActionListener { textView, i, keyEvent ->
                    val name = etName.text.toString()
                    val age = etAge.text.toString().toInt()
                    val korean = text.toString().toInt()
                    val student = Student(name, age, korean)
                    studentList.add(student)


                    etName.requestFocus()
                    etName.setText("")
                    etAge.setText("")
                    setText("")
                    false
                }
            }
        }

    }

    inner class RecyclerAdapterClass:RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>(){

        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root){

            var tv_nameRow : TextView
            var tv_ageRow : TextView
            var tv_koreanRow : TextView
            var buttonRow : Button

            init {
                tv_nameRow = rowBinding.tvNameRow
                tv_ageRow = rowBinding.tvAgeRow
                tv_koreanRow = rowBinding.tvKoreanRow
                buttonRow = rowBinding.buttonRow
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            val params = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
            rowBinding.root.layoutParams = params

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return studentList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.tv_nameRow.text = "이름 : ${studentList[position].name}"
            holder.tv_ageRow.text = "나이 : ${studentList[position].age.toString()}"
            holder.tv_koreanRow.text = "국어 점수 : ${studentList[position].korean.toString()}"

            holder.buttonRow.run {
                setOnClickListener{
                    studentList.removeAt(position)
                    notifyDataSetChanged()
                }
            }
        }
    }
}
data class Student(var name:String, var age:Int, var korean:Int)