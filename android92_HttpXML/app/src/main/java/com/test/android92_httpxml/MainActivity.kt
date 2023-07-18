package com.test.android92_httpxml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android92_httpxml.databinding.ActivityMainBinding
import org.w3c.dom.Element
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // https://mixedanalytics.com/blog/list-actually-free-open-no-auth-needed-apis/ 에서 212번 api
    val serverAddress = "https://www.aviationweather.gov/adds/dataserver_current/httpparam?datasource=metars&requestType=retrieve&format=xml&mostRecentForEachStation=constraint&hoursBeforeNow=1.25&stationString=KDE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.setOnClickListener {
                thread {
                    // 접속 주소를 관리하는 객체를 생성
                    val url = URL(serverAddress)
                    // 접속
                    val httpUrlConnection = url.openConnection() as HttpURLConnection

                    // 웹 브라우저 종류를 확인할 수도 있기에
                    httpUrlConnection.addRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")

                    // DOM 방식으로 XML 문서를 분석할 수 있는 도구 생성
                    // DOM(Document Object Model)은 웹 페이지의 구조와 콘텐츠에 접근하고 조작하기 위한 프로그래밍 인터페이스를 제공하는 표준이자 API
                    val documentBuilderFactory = DocumentBuilderFactory.newInstance()
                    val documentBuilder = documentBuilderFactory.newDocumentBuilder()
                    // 분석 도구를 이용해 XML 문서를 분석해 각 태그들을 모두 객체를 생성
                    // 태그들을 관리하는 객체 반환
                    val document = documentBuilder.parse(httpUrlConnection.inputStream)

                    // XML문서의 최상위 태그 <response>
                    val root = document.documentElement
                    // data tag 가져오기 <data>
                    val dataTag = root.getElementsByTagName("data")
                    // METAR tag 가져오기 <METAR>
                    val dataElement = dataTag.item(0) as Element
                    val METATag = dataElement.getElementsByTagName("METAR")

                    runOnUiThread {
                        textView.text = ""
                    }

                    // 태그의 수 만큼 반복
                    for(idx in 0 until METATag.length){
                        // idx 번째 태그 객체
                        val METAElement = METATag.item(idx) as Element

                        // META 태그 내에서 필요한 태그
                        val rawTextList = METAElement.getElementsByTagName("raw_text")
                        val stationIdList = METAElement.getElementsByTagName("station_id")
                        val latitudeList = METAElement.getElementsByTagName("latitude")
                        val longitudeList = METAElement.getElementsByTagName("longitude")

                        val rowTextTag = rawTextList.item(0) as Element
                        val stationIdTag = stationIdList.item(0) as Element
                        val latitudeTag = latitudeList.item(0) as Element
                        val longitudeTag = longitudeList.item(0) as Element

                        // 태그내의 데이터 가져오기
                        val rowText = rowTextTag.textContent
                        val stationId = stationIdTag.textContent
                        val latitude = latitudeTag.textContent.toDouble()
                        val longitude = longitudeTag.textContent.toDouble()

                        runOnUiThread {
                            textView.append("""
                                rowText : $rowText
                                stationId : $stationId
                                latitude : $latitude
                                logitude : $longitude
                                ---------------------------
                                
                            """.trimIndent())
                        }
                    }
                }
            }
        }
    }
}