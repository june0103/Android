package com.test.android_ctgr_memo_sqlite

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android_ctgr_memo_sqlite.databinding.DialogCtgrAddBinding
import com.test.android_ctgr_memo_sqlite.databinding.FragmentMainBinding
import com.test.android_ctgr_memo_sqlite.databinding.FragmentMemoListBinding
import com.test.android_ctgr_memo_sqlite.databinding.RowMainBinding
import com.test.android_ctgr_memo_sqlite.databinding.RowMemoBinding


class MemoListFragment : Fragment() {

    lateinit var fragmentMemoListBinding: FragmentMemoListBinding
    lateinit var mainActivity: MainActivity

    var memoList = mutableListOf<MemoClass>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMemoListBinding = FragmentMemoListBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        val ctgr = DAO.selectCtgr(requireContext(),mainActivity.ctgrRowPosition)

        fragmentMemoListBinding.run {
            toolbarMemoList.run {
                title = "${ctgr.title}"
                setTitleTextColor(Color.WHITE)
                // 백 버튼 아이콘을 표시한다.
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                // 백 버튼 아이콘 색상
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                    // BlendModeColorFilter(투명색이 아닌부분을 흰색으로)
                    navigationIcon?.colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
                } else{
                    navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                }
                inflateMenu(R.menu.main_menu)

                // 메모 추가
                setOnMenuItemClickListener {
                    mainActivity.replaceFragment(MainActivity.ADD_FRAGMENT,true,true)
                    false
                }

                // 뒤로가기
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.MEMOLIST_FRAGMENT)
                }
            }

            recyclerViewMemoList.run {
                adapter = MainRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                addItemDecoration(
                    DividerItemDecoration(
                        mainActivity,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }
        // Inflate the layout for this fragment
        return fragmentMemoListBinding.root
    }

    inner class MainRecyclerViewAdapter :
        RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolderClass>() {

        inner class MainViewHolderClass(rowMemoBinding: RowMemoBinding) :
            RecyclerView.ViewHolder(rowMemoBinding.root) {
            var textViewMemoRowTitle: TextView

            init {
                textViewMemoRowTitle = rowMemoBinding.textViewMemoRowTitle

                rowMemoBinding.root.setOnClickListener {
                    val memo = memoList[adapterPosition]
                    val memoidx = memo.idx
                    mainActivity.memoRowPosition = memoidx
                    mainActivity.replaceFragment(MainActivity.RESULT_FRAGMENT, true, true)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolderClass {
            val rowMemoBinding = RowMemoBinding.inflate(layoutInflater)
            val mainViewHolderClass = MainViewHolderClass(rowMemoBinding)

            rowMemoBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return mainViewHolderClass
        }

        override fun getItemCount(): Int {
            return memoList.size
        }

        override fun onBindViewHolder(holder: MainViewHolderClass, position: Int) {
            holder.textViewMemoRowTitle.text = memoList[position].title
        }
    }

    override fun onResume() {
        super.onResume()

        memoList = DAO.selectAllData(requireContext(),mainActivity.ctgrRowPosition)

        // 리사이클러뷰 갱신
        fragmentMemoListBinding.recyclerViewMemoList.adapter?.notifyDataSetChanged()
    }
}