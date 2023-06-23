package com.test.android44_ex01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.android44_ex01.databinding.ActivityMainBinding
import com.test.android44_ex01.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)



        activityMainBinding.run {
            recyclerView.run {
                adapter = RecyclerAdapterClass()
                layoutManager = LinearLayoutManager(this@MainActivity)

            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.mainMenuAdd -> {
                val addIntent = Intent(this@MainActivity, AddActivity::class.java)
                startActivity(addIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    inner class RecyclerAdapterClass :
        RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>() {

        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root) {
            var textView: TextView

            init {
                textView = rowBinding.textViewRow

                // 아이템 클릭
                rowBinding.root.setOnClickListener {
                    val viewIntent = Intent(this@MainActivity, ViewActivity::class.java)

                    // 사용자가 터치한 항목의 순서값을 담아준다
                    viewIntent.putExtra("position",adapterPosition)
                    startActivity(viewIntent)

                }
            }


        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            val params = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT)
            rowBinding.root.layoutParams = params

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return DataClass.fruitList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            //holder.textView.text = fruitList[position].kind

            holder.textView.text = DataClass.fruitList[position].kind

//            holder.itemView.setOnClickListener {
//                val viewIntent = Intent(this@MainActivity, ViewActivity::class.java)
//                viewIntent.putExtra("kind", fruitList[position].kind)
//                viewIntent.putExtra("num", fruitList[position].num)
//                viewIntent.putExtra("country", fruitList[position].country)
//                startActivity(viewIntent)
//
//
//                Log.d("lion",fruitList[position].kind)
//                Log.d("lion",fruitList[position].num.toString())
//                Log.d("lion",fruitList[position].country)
//            }
        }


    }

    override fun onResume() {
        super.onResume()

        val adapter = activityMainBinding.recyclerView.adapter as RecyclerAdapterClass
        adapter.notifyDataSetChanged()
    }
}