package com.test.android36_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.test.android36_recyclerview.databinding.ActivityMainBinding
import com.test.android36_recyclerview.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    val imgRes = intArrayOf(
        R.drawable.imgflag1 , R.drawable.imgflag2 , R.drawable.imgflag3 ,
        R.drawable.imgflag4 , R.drawable.imgflag5 , R.drawable.imgflag6 ,
        R.drawable.imgflag7 , R.drawable.imgflag8, R.drawable.imgflag1 , R.drawable.imgflag2 , R.drawable.imgflag3 ,
        R.drawable.imgflag4 , R.drawable.imgflag5 , R.drawable.imgflag6 ,
        R.drawable.imgflag7 , R.drawable.imgflag8
    )

    val data1 = arrayOf("토고", "프랑스 프랑스 프랑스 프랑스 프랑스 프랑스 프랑스 프랑스 ", "스위스", "스페인", "일본", "독일", "브라질", "대한민국",
        "토고", "프랑스", "스위스", "스페인", "일본", "독일", "브라질", "대한민국")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            recyclerView.run {
                adapter = RecyclerAdapterClass()

                // RecyclerView의 항목을 어떤 방식으로 보여줄지 정해준다
                // LinareLayout - 위에서 아래 방향으로
                 layoutManager = LinearLayoutManager(this@MainActivity)

                // Grid - 한줄에 몇 칸을 사용할 것인지
                // layoutManager = GridLayoutManager(this@MainActivity,2)

                // 항목 View의 크기가 다를 경우 GridLayoutManager는 같은 행의 모든 뷰가 같은 크기로 조정되지만
                // StaggeredGridLayoutManager는 항목 View의 크기는 필요한 만큼만 사용하고 화면에 빈칸이 없도록 배치를 조정해준다
                // layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


            }
        }

    }

    // RecyclerView의 adapterClass
    // 1. 아무것도 상속받지 않은 클래스 생성
    // 2. ViewHolder 생성
    // 3. AdapterClass를 RecyclerView.Adapter를 상속받게 한다
    inner class RecyclerAdapterClass : RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>(){

        // RecyclerView의 Row 하나가 가지고 있는 View들의 객체를 가지고있는 HolderClass
        // 주 생성자로 ViewBinding 객체를 받는다
        // 부모의 생성자에게 행 하나로 사용할 View를 전달
        inner class ViewHolderClass(rowBinding: RowBinding) : ViewHolder(rowBinding.root), OnClickListener {
//            // 행 하나를 구성하는 View 중 개발자가 사용하고자 하는 View 객체를 담을 변수
//            lateinit var imageViewRow:ImageView
//            lateinit var textViewRow: TextView
//
//            // 부모클래스에 행으로 사용할 View를 전달
//            constructor(rowBinding: RowBinding) : super(rowBinding.root){
//                // 사용할 view를 변수에 담아준다
//                imageViewRow = rowBinding.imageViewRow
//                textViewRow = rowBinding.textViewRow
//
//            }

            var textViewRow : TextView
            var imageViewRow : ImageView

            init{
                textViewRow = rowBinding.textViewRow
                imageViewRow = rowBinding.imageViewRow
            }

            override fun onClick(p0: View?) {
                // ViewHolder를 통해 항목의 순서값을 가지고 온다
                activityMainBinding.run {
                    textView.run {
                        text = data1[adapterPosition]
                    }
                }
            }


        }

        // ViewHolder의 객체를 생성해서 반환
        // 전체 Row의 개수가 아닌 필요한 만큼만 Row로 사용할 View를 만들고 ViewHolder도 생성
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {

            // ViewBInding
            val rowBinding = RowBinding.inflate(layoutInflater)
            // ViewHolder
            val viewHolderClass = ViewHolderClass(rowBinding)

            // 클릭 이벤트를 설정
            rowBinding.root.setOnClickListener(viewHolderClass)

            // 항목 View의 가로 세로 길이를 해당구역 터치하기 위해 설정 ( 가로 , 세로)
            val params = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
            rowBinding.root.layoutParams = params

            return viewHolderClass
        }

        // 전체Row의 개수를 반환
        override fun getItemCount(): Int {
            return imgRes.size
        }

        // ViewHolder를 통해 View에 접근하여 View에 값을 설정
        // 첫 번째 매개변수 : ViewHolder 객체
        // 두 번째 매개변수 : 특정 row의 순서값
        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.textViewRow.text = data1[position]
            holder.imageViewRow.setImageResource(imgRes[position])

        }
    }

}