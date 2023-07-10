package com.test.android_ctgr_memo_sqlite

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.get
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android_ctgr_memo_sqlite.databinding.DialogCtgrAddBinding
import com.test.android_ctgr_memo_sqlite.databinding.FragmentMainBinding
import com.test.android_ctgr_memo_sqlite.databinding.RowMainBinding
import kotlin.concurrent.thread


class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    // 카테고리 담을 리스트
    var ctgrList = mutableListOf<CtgrClass>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentMainBinding.run {
            toolbarMain.run {
                title = "카테고리 목록"
                setTitleTextColor(Color.WHITE)
                inflateMenu(R.menu.main_menu)
                setOnMenuItemClickListener {
                    // 새로운 카테고리를 다이얼로그를 띄어 생성
                    val dialogBinding = DialogCtgrAddBinding.inflate(layoutInflater)
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle("카테고리 입력")
                    builder.setView(dialogBinding.root)
                    dialogBinding.etDialogCtgrTitle.requestFocus()

                    builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                        val ctgrTitle = dialogBinding.etDialogCtgrTitle.text.toString()
                        DAO.insertCategory(requireContext(),ctgrTitle)
                        // 리사이클러뷰 갱신을위해 저장하고 바로 값을 받아옴
                        ctgrList = DAO.selectAllCtgr(requireContext())
                        fragmentMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                    }
                    builder.setNegativeButton("취소",null)
                    builder.show()

                    false
                }
            }

            recyclerViewMain.run {
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
        return fragmentMainBinding.root
    }

    inner class MainRecyclerViewAdapter :
        RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolderClass>() {

        inner class MainViewHolderClass(rowMainBinding: RowMainBinding) :
            RecyclerView.ViewHolder(rowMainBinding.root) {
            var textViewMainRowTitle: TextView

            init {
                textViewMainRowTitle = rowMainBinding.textViewMainRowTitle

                // 롱클릭시 컨텍스트메뉴 ( 카테고리 수정, 삭제)
                rowMainBinding.root.setOnCreateContextMenuListener { menu, view, menuInfo ->
                    menu.setHeaderTitle("카테고리 메뉴")
                    activity?.menuInflater?.inflate(R.menu.context_menu, menu)

                    val ctgr = DAO.selectCtgr(requireContext(),ctgrList[adapterPosition].idx)
                    // 수정 : 0, 삭제 : 1
                    // 수정
                    menu[0].setOnMenuItemClickListener {
                        val dialogBinding = DialogCtgrAddBinding.inflate(layoutInflater)
                        val builder = AlertDialog.Builder(requireContext())
                        builder.setTitle("카테고리 수정")
                        builder.setView(dialogBinding.root)
                        dialogBinding.etDialogCtgrTitle.requestFocus()
                        dialogBinding.etDialogCtgrTitle.setText(ctgr.title)

                        builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                            ctgr.title = dialogBinding.etDialogCtgrTitle.text.toString()

                            DAO.updateCtgr(requireContext(),ctgr)

                            ctgrList = DAO.selectAllCtgr(requireContext())
                            fragmentMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                        }
                        builder.setNegativeButton("취소",null)
                        builder.show()

                        false
                    }
                    // 삭제
                    menu[1].setOnMenuItemClickListener {
                        // 해당카테고리에 생성된 메모까지 삭제하도록 DAO에 설정
                        DAO.deleteCtgr(requireContext(),ctgr.idx)

                        ctgrList = DAO.selectAllCtgr(requireContext())
                        fragmentMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                        Toast.makeText(requireContext(),"삭제 완료",Toast.LENGTH_SHORT).show()
                        false
                    }
                }

                // 데이터 클릭시 메모리스트로
                rowMainBinding.root.setOnClickListener {
                    val ctgr = ctgrList[adapterPosition]
                    val ctgridx = ctgr.idx
                    mainActivity.ctgrRowPosition = ctgridx
                    mainActivity.replaceFragment(MainActivity.MEMOLIST_FRAGMENT, true, true)
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
            return ctgrList.size
        }

        override fun onBindViewHolder(holder: MainViewHolderClass, position: Int) {
            holder.textViewMainRowTitle.text = ctgrList[position].title
        }
    }

    override fun onResume() {
        super.onResume()
        ctgrList = DAO.selectAllCtgr(requireContext())
        // 리사이클러뷰 갱신
        fragmentMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
    }
}