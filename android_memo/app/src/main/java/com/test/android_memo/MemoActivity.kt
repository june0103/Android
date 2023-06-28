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
import com.test.android_memo.databinding.ActivityMemoBinding
import com.test.android_memo.databinding.RowMemoBinding

class MemoActivity : AppCompatActivity() {

    lateinit var memoBinding: ActivityMemoBinding
    // key값
    lateinit var ctgrName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        memoBinding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(memoBinding.root)
        // 액션바를 사용하도록 설정
        supportActionBar?.setDisplayShowTitleEnabled(true)

        memoBinding.run {
            // 해당카테고리(key)
            ctgrName = intent.getStringExtra("ctgrName").toString()

            // 액션바 제목 변경
            supportActionBar?.title = "${ctgrName}의 메모 목록"

            // 리사이클러뷰
            recyclerViewMemo.run {
                adapter = RecyclerAdapterClass()
                layoutManager = LinearLayoutManager(this@MemoActivity)
            }
        }
    }

    // 옵션메뉴
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.memo_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        memoBinding.run {
            when (item.itemId) {
                // 메모 추가
                R.id.menu_memo_add -> {
                    val memoAddIntent = Intent(this@MemoActivity, AddMemoActivity::class.java)
                    memoAddIntent.putExtra("ctgrName", ctgrName)
                    startActivity(memoAddIntent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }



    inner class RecyclerAdapterClass: RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>(){

        inner class ViewHolderClass(rowMemoBinding: RowMemoBinding): RecyclerView.ViewHolder(rowMemoBinding.root){

            var tv_memo_name : TextView
            init {
                tv_memo_name = rowMemoBinding.tvMemoName

                // 롱클릭시 컨텍스트메뉴 ( 메모 수정, 삭제)
                rowMemoBinding.root.setOnCreateContextMenuListener { menu, view, menuInfo ->

                    menu.setHeaderTitle("${DataClass.ctgrMap[ctgrName]?.get(adapterPosition)?.name}의 메모 메뉴")
                    menuInflater.inflate(R.menu.context_memo, menu)

                    // 수정 : 0, 삭제 : 1
                    // 수정
                    menu[0].setOnMenuItemClickListener {
                        val memomodiIntent =
                            Intent(this@MemoActivity, ModifyMemoActivity::class.java)
                        // map의 카테고리와 메모리스트의 인덱스를 넘겨준다
                        memomodiIntent.putExtra("ctgrName", ctgrName)
                        memomodiIntent.putExtra("memoIndex", adapterPosition)

                        startActivity(memomodiIntent)
                        false
                    }
                    // 삭제
                    menu[1].setOnMenuItemClickListener {
                        // map에서 특정카테고리의 메모리스트에서 해당하는 인덱스의 항목을 삭제
                        DataClass.ctgrMap[ctgrName]?.removeAt(adapterPosition)
                        notifyDataSetChanged()
                        false
                    }
                }

                // 아이템 클릭
                rowMemoBinding.root.setOnClickListener {
                    val memoViewIntent = Intent(this@MemoActivity, MemoItemView::class.java)
                    // map의 카테고리와 메모리스트의 인덱스를 넘겨준다
                    memoViewIntent.putExtra("ctgrName", ctgrName)
                    memoViewIntent.putExtra("memoIndex", adapterPosition)

                    startActivity(memoViewIntent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowMemoBinding = RowMemoBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowMemoBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT)
            rowMemoBinding.root.layoutParams = params
            return viewHolderClass
        }

        override fun getItemCount(): Int {
            // DataClass.ctgrMap[ctgrName]?.size가 null이 아니면 해당 값의 크기를 반환하고, null이면 0을 반환
            return DataClass.ctgrMap[ctgrName]?.size ?: 0
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.tv_memo_name.text = DataClass.ctgrMap[ctgrName]?.get(position)?.name
        }

    }

    override fun onResume() {
        super.onResume()

        val adapter = memoBinding.recyclerViewMemo.adapter as MemoActivity.RecyclerAdapterClass
        adapter.notifyDataSetChanged()
    }
}