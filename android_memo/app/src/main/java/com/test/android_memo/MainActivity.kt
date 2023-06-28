package com.test.android_memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android_memo.databinding.ActivityMainBinding
import com.test.android_memo.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 액션바를 사용하도록 설정
        supportActionBar?.setDisplayShowTitleEnabled(true)

        activityMainBinding.run {
            // 액션바 제목 변경
            supportActionBar?.title = "카테고리 목록"
            // 리사이클러뷰
            recyclerViewCtgr.run {
                adapter = RecyclerAdapterClass()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    // 옵션메뉴
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        activityMainBinding.run {
            when (item.itemId) {
                // 카테고리 추가
                R.id.menu_ctgr_add -> {
                    val ctgraddIntent = Intent(this@MainActivity, AddCtgrActivity::class.java)
                    startActivity(ctgraddIntent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 리사이클러뷰 어댑터
    inner class RecyclerAdapterClass :
        RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>() {

        inner class ViewHolderClass(rowBinding: RowBinding) :
            RecyclerView.ViewHolder(rowBinding.root) {
            var tv_ctgr_name: TextView
            init {
                tv_ctgr_name = rowBinding.tvCtgrName

                // 롱클릭시 컨텍스트메뉴 ( 카테고리 수정, 삭제)
                rowBinding.root.setOnCreateContextMenuListener { menu, view, menuInfo ->
                    menu.setHeaderTitle("${DataClass.ctgrMap.keys.toList()[adapterPosition]}의 카테고리 메뉴")
                    menuInflater.inflate(R.menu.context_menu, menu)

                    // 수정 : 0, 삭제 : 1
                    // 수정
                    menu[0].setOnMenuItemClickListener {
                        val ctgrmodiIntent =
                            Intent(this@MainActivity, ModifyCtgrActivity::class.java)
                        // 수정하기위해 필요한 map의 key값
                        //ctgrmodiIntent.putExtra("ctgrName", ctgrList[adapterPosition])
                        ctgrmodiIntent.putExtra("ctgrName", DataClass.ctgrMap.keys.toList()[adapterPosition])
                        startActivity(ctgrmodiIntent)
                        false
                    }
                    // 삭제
                    menu[1].setOnMenuItemClickListener {
                        // index에 해당하는 위치의 카테고리이름(key)
                        val ctgrName = DataClass.ctgrMap.keys.toList()[adapterPosition]
                        // 해당 카테고리(key) 제거
                        DataClass.ctgrMap.remove(ctgrName)
                        notifyDataSetChanged()
                        false
                    }
                }

                // 카테고리 클릭시 해당 카테고리의 이름 즉 key값을 넘겨준다
                rowBinding.root.setOnClickListener {
                    val memoIntent = Intent(this@MainActivity, MemoActivity::class.java)
                    memoIntent.putExtra("ctgrName", DataClass.ctgrMap.keys.toList()[adapterPosition])
                    startActivity(memoIntent)
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
            // map의 크기
            return DataClass.ctgrMap.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            // 카테고리이름
            holder.tv_ctgr_name.text = DataClass.ctgrMap.keys.toList()[position]
        }
    }


    override fun onResume() {
        super.onResume()
        // 변경된 데이터 반영
        val adapter = activityMainBinding.recyclerViewCtgr.adapter as RecyclerAdapterClass
        adapter.notifyDataSetChanged()
    }


}
