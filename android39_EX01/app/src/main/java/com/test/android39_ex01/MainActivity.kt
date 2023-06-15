package com.test.android39_ex01

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android39_ex01.databinding.ActivityMainBinding
import com.test.android39_ex01.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val playerList = mutableListOf<Player>()
    val nationList = arrayOf("토고","프랑스","스위스","스페인","일본","독일","브라질","대한민국")
    val imagList = arrayOf(R.drawable.imgflag1,
        R.drawable.imgflag2,
        R.drawable.imgflag3,
        R.drawable.imgflag4,
        R.drawable.imgflag5,
        R.drawable.imgflag6,
        R.drawable.imgflag7,
        R.drawable.imgflag8)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        activityMainBinding.run {
            inputView.visibility = View.GONE
            recyclerView.visibility = View.GONE
            btInput.run {
                setOnClickListener{
                    inputView.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
            }

            btView.run {
                setOnClickListener{
                    inputView.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE

                    val adapter = recyclerView.adapter  as RecyclerAdapterClass
                    adapter.notifyDataSetChanged()
                }
            }

            recyclerView.run {
                adapter = RecyclerAdapterClass()
                layoutManager= LinearLayoutManager(this@MainActivity)
            }

            spinner.run {
                val a1 = ArrayAdapter<String>(
                    this@MainActivity,
                    // Spinner 접혀 있을 때 모양
                    android.R.layout.simple_spinner_item,
                    nationList
                )
                // spinner 펼쳐져 있을 때 모양
                a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                adapter = a1

                // Spinner의 항목을 코드로 선택
                // 0부터 시작하는 순서값을 넣어준다.
                setSelection(7)
            }

            etName.run {
                setOnEditorActionListener { textView, i, keyEvent ->
                    val name = text.toString()
                    var role = ""
                    when (radioInput.checkedRadioButtonId) {
                        R.id.radio_fw -> {
                            role = "공격수"
                        }

                        R.id.radio_mf -> {
                            role = "미드필더"
                        }

                        R.id.radio_df -> {
                            role = "수비수"
                        }

                        R.id.radio_gk -> {
                            role = "골키퍼"
                        }
                    }
                    var nation = nationList[spinner.selectedItemPosition]

                    Log.d("dkr",nation)
                    Log.d("dkr","z $nation")
                    val player = Player(nation, role, name)
                    playerList.add(player)

                    setText("")
                    false
                }
            }
        }
    }

    inner class RecyclerAdapterClass: RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>(){

        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root){

            var imageView : ImageView
            var tv_nameRow : TextView
            var tv_roleRow : TextView

            init {
                tv_nameRow = rowBinding.tvNameRow
                imageView = rowBinding.imageView
                tv_roleRow = rowBinding.tvRoleRow
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
            return playerList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.tv_nameRow.text = "이름 : ${playerList[position].name}"
            holder.tv_roleRow.text = "포지션 : ${playerList[position].role}"
            var nationImg = 0
            Log.d("dkr",playerList[position].nation)
            when(playerList[position].nation){
                "토고" -> nationImg = R.drawable.imgflag1
                "프랑스" -> nationImg = R.drawable.imgflag2
                "스위스" -> nationImg = R.drawable.imgflag3
                "스페인" -> nationImg = R.drawable.imgflag4
                "일본" -> nationImg = R.drawable.imgflag5
                "독일" -> nationImg = R.drawable.imgflag6
                "브라질" -> nationImg = R.drawable.imgflag7
                "대한민국" -> nationImg = R.drawable.imgflag8
            }
            holder.imageView.setImageResource(nationImg)
        }
    }
}

data class Player(var nation:String, var role:String, var name: String )