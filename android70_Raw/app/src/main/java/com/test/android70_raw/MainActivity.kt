package com.test.android70_raw

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android70_raw.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 사운드 재생 관리 객체
    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.setOnClickListener {
                // raw 폴더에 있는 파일과 연결된 스트림 추출
                val inputStream = resources.openRawResource(R.raw.data)
                val inputStreamReader = InputStreamReader(inputStream, "UTF-8")
                val bufferedReader = BufferedReader(inputStreamReader)

                var str:String? = null
                val stringBuffer = StringBuffer()

                do {
                    str = bufferedReader.readLine()

                    if(str != null){
                        stringBuffer.append("${str}\n")
                    }
                }while (str != null)

                bufferedReader.close()

                textView.text = stringBuffer.toString()

            }

            button2.setOnClickListener {
                if(mediaPlayer == null){
                    // 사운드 재생 관리 객체를 생성
                    mediaPlayer = MediaPlayer.create(this@MainActivity,R.raw.song)
                    // 재생
                    mediaPlayer?.start()
                }
            }

            button3.setOnClickListener {
                if(mediaPlayer != null){
                    // 사운드 중지
                    mediaPlayer?.stop()
                    mediaPlayer = null
                }
            }

            button4.setOnClickListener {
                // VideoView가 재생중이 아니라면
                if(videoView.isPlaying == false){
                    // 영상 파일의 경로를 가져온다
                    val uri = Uri.parse("android.resource://${packageName}/raw/video")
                    // 영상 주소를 설정한다.
                    videoView.setVideoURI(uri)
                    // 재생한다.
                    videoView.start()
                }
            }

            button5.setOnClickListener {
                // VideoView가 재생 중이라면
                if(videoView.isPlaying == true) {
                    // 영상 재생을 중지한다.
                    videoView.stopPlayback()
                }
            }
        }
    }
}