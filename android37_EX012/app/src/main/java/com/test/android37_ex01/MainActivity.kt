package com.test.android37_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android37_ex01.databinding.ActivityMainBinding
import com.test.android37_ex01.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val studentList = mutableListOf<StudentInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            // 두 개의 LinearLayout을 모두 사라진 상태로 한다.
            linearLayout1.visibility = View.GONE
            linearLayout2.visibility = View.GONE

            buttonAdd.run{
                setOnClickListener {
                    linearLayout1.visibility = View.VISIBLE
                    linearLayout2.visibility = View.GONE

                    // 입력칸을 모두 비운다.
                    editTextName.setText("")
                    editTextAge.setText("")
                    editTextKorean.setText("")

                    // 포커스를 준다.
                    editTextName.requestFocus()
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(editTextName, 0)
                }
            }

            buttonShow.run{
                setOnClickListener {
                    // 포커스를 제거하고 키보드를 내린다.
                    if(currentFocus != null) {
                        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                        currentFocus!!.clearFocus()
                    }

                    linearLayout1.visibility = View.GONE
                    linearLayout2.visibility = View.VISIBLE

                    val adapter = recylerViewResult.adapter  as RecyclerAdapterClass
                    adapter.notifyDataSetChanged()
                }
            }

            editTextKorean.run{
                setOnEditorActionListener { v, actionId, event ->

                    val name = editTextName.text.toString()
                    val age = editTextAge.text.toString().toInt()
                    val korean = editTextKorean.text.toString().toInt()

                    val studentInfo = StudentInfo(name, age, korean)
                    studentList.add(studentInfo)
                    Log.d("abcdkkk", "${studentList.size}")

                    editTextName.setText("")
                    editTextAge.setText("")
                    editTextKorean.setText("")

                    editTextName.requestFocus()

                    false
                }
            }

            recylerViewResult.run{
                adapter = RecyclerAdapterClass()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    data class StudentInfo(var name:String, var age:Int, var korean:Int)

    inner class RecyclerAdapterClass : RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>() {

        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root){

            var textViewName:TextView
            var textViewAge:TextView
            var textViewkorean:TextView
            var buttonRowRemove: Button

            init {
                textViewName = rowBinding.textViewName
                textViewAge = rowBinding.textViewAge
                textViewkorean = rowBinding.textViewKorean
                buttonRowRemove = rowBinding.buttonRowRemove
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
            val (name, age, korean) = studentList[position]

            holder.textViewName.text = name
            holder.textViewAge.text = "${age}살"
            holder.textViewkorean.text = "${korean}점"

            holder.buttonRowRemove.setOnClickListener {
                studentList.removeAt(position)

                val adapter = activityMainBinding.recylerViewResult.adapter as RecyclerAdapterClass
                adapter.notifyDataSetChanged()
            }

        }
    }

}











