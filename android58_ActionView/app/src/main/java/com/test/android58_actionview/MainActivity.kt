package com.test.android58_actionview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import com.test.android58_actionview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val data1 = arrayOf(
        "aaaa", "bbbb", "cccc", "aabb", "ccdd"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            list1.run {
                list1.adapter = ArrayAdapter<String>(
                    this@MainActivity, android.R.layout.simple_list_item_1, data1
                )
                //리스트뷰가 검색이 가능하도록 설정
                isTextFilterEnabled = true

                setOnItemClickListener { adapterView, view, i, l ->
                    // 리스트뷰에서 position 번재 항목의 문자열값을 가져온다
                    val str1 = adapterView.adapter.getItem(i) as String
                    // 해당 문자열이 몇 번째에 있는지 확인
                    val idx = data1.indexOf(str1)
                    activityMainBinding.textView3.text = data1[idx]
                }
            }


        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        // SearchView를 가지고 있는 MenuItem을 가져옴
        val item1 = menu?.findItem(R.id.item1)
        // SearchView 추출
        val searchView = item1?.actionView as SearchView
        // 안내문구 설정
        searchView.queryHint = "검색어 입력"

        // ActionView가 펼쳐지거나 접혔을 때
        item1.setOnActionExpandListener(object : MenuItem.OnActionExpandListener{
            // true를 반환하면 펼쳐지고 false를 반환하면 펼치지지 않는다.
            override fun onMenuItemActionExpand(p0: MenuItem): Boolean {
                activityMainBinding.textView.text = "펼쳐 졌을 때"
                return true
            }

            // true를 반환하면 접혀지고 false를 반환하면 접혀지지 않는다.
            override fun onMenuItemActionCollapse(p0: MenuItem): Boolean {
                activityMainBinding.textView.text = "접혀 졌을 때"
                return true
            }
        })

        // SearchView 리스너
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            // 문자열 입력이 완료되었을 때
            override fun onQueryTextSubmit(query: String?): Boolean {
                activityMainBinding.textView.text = "문자열 입력 완료"
                activityMainBinding.textView2.text = "입력 완료 : $query"
                searchView.clearFocus()

                // ActionView를 접어준다
                item1.collapseActionView()
                // ActionView를 펼친다
                // item1.expandActionView()
                return true
            }

            // 입력 중에 호출되는 메서드
            override fun onQueryTextChange(newText: String?): Boolean {
                activityMainBinding.textView.text = "문자열 입력중"
                activityMainBinding.textView2.text = "입력 중 : $newText"

                // ListView에 검색어 설정
                activityMainBinding.list1.setFilterText(newText)
                if(newText?.length == 0){
                    activityMainBinding.list1.clearTextFilter()
                }

                return true
            }

        })



        return super.onCreateOptionsMenu(menu)
    }
}