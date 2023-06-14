package com.test.android33_ex01

import android.inputmethodservice.Keyboard.Row
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.test.android33_ex01.databinding.ActivityMainBinding
import com.test.android33_ex01.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    var dataList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {


            listView.run {
                adapter = CustomAdapter()
            }
            editTextText.run {
                setOnEditorActionListener { textView, i, keyEvent ->
                    dataList.add(editTextText.text.toString())

                    editTextText.setText("")
                    false
                }
            }
        }

    }

    inner class CustomAdapter : BaseAdapter(){
        override fun getCount(): Int {
            return dataList.size
        }

        override fun getItem(p0: Int): Any? {
            return null
        }

        override fun getItemId(p0: Int): Long {
            return 0
        }

        override fun getView(position: Int, converView: View?, p2: ViewGroup?): View {
            var rowBinding : RowBinding? = null
            var mainView = converView
            if(mainView == null){
                rowBinding = RowBinding.inflate(layoutInflater)
                mainView = rowBinding.root
                mainView!!.tag = rowBinding
            }
            else{
                rowBinding = mainView.tag as RowBinding
            }

            rowBinding.run{
                textViewRow.run {
                    text = dataList[position]
                }

                buttonRow.run {
                    setOnClickListener {
                        dataList.removeAt(position)
                        notifyDataSetChanged()
                    }
                }


            }

            return mainView
        }

    }
}