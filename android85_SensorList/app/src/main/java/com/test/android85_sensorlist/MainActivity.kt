package com.test.android85_sensorlist

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android85_sensorlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 센서 관리자
        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // 단말기의 센서 목록
        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)

        activityMainBinding.textView.run {
            text = ""

            for(sensor in sensorList){
                append("센서 이름 : ${sensor.name}\n")
                append("센서 종류 : ${sensor.type}\n\n")
            }
        }

    }
}