package com.test.android87_compas

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android87_compas.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    var sensorListener: SensorEventListener? = null

    // 가속도계 센서값
    private val accelerometerReading = FloatArray(3)
    // 자기장 센서값
    private val magnetometerReading = FloatArray(3)
    // 센서값에서 방향을 계산하기 위한 변수
    private val rotationMatrix = FloatArray(9)
    private val orientationAngles = FloatArray(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.run {
                setOnClickListener {

                    val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
                    // 가속도 센서
                    val accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                    // 자기장 센서
                    val magnetometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

                    if (sensorListener == null) {
                        // 리스너
                        sensorListener = object : SensorEventListener {
                            override fun onSensorChanged(p0: SensorEvent?) {
                                if (p0 == null) return

                                if (p0.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                                    // 가속도 센서 값을 복사
                                    System.arraycopy(p0?.values, 0, accelerometerReading, 0, p0?.values!!.size)
                                } else if (p0.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
                                    // 자기장 센서 값을 복사
                                    System.arraycopy(p0?.values, 0, magnetometerReading, 0, p0?.values!!.size)
                                }

                                //
                                SensorManager.getRotationMatrix(rotationMatrix, null, accelerometerReading, magnetometerReading)
                                SensorManager.getOrientation(rotationMatrix, orientationAngles)

                                val azimuthDegree = Math.toDegrees(orientationAngles[0].toDouble()).toFloat()

                                // 방위각을 0~360도로 변환
                                val azimuth360 = (azimuthDegree + 360) % 360

                                imageView.rotation = -azimuth360
                                textView3.text = "Compass: ${azimuth360.roundToInt()}°"
                            }

                            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

                            }
                        }
                    }
                    val chk1 = sensorManager.registerListener(
                        sensorListener,
                        accelerometerSensor,
                        SensorManager.SENSOR_DELAY_UI
                    )
                    val chk2 = sensorManager.registerListener(
                        sensorListener,
                        magnetometerSensor,
                        SensorManager.SENSOR_DELAY_UI
                    )
                    if (chk1 == false) {
                        sensorListener = null
                        textView.text = "가속도 센서를 지원하지 않습니다"
                    }
                    if (chk2 == false) {
                        sensorListener = null
                        textView2.text = "자기장 센서를 지원하지 않습니다"
                    }
                }
            }
        }
    }
}