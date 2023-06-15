package com.test.android38_permission

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.test.android38_permission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 확인 받을 권환 목록
    // 이 권한 목록 중에 확인이 불필요하거나 이미 허용되어 있는 권한은 제외
    val permissionList = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.WRITE_CONTACTS,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    // 모든 권한에 대해서 한번에 요청하고자 한다면 requestPermission 메서드를 사용
    // 권한 확인 후에 처리가 필요하다면 onRequestPermissionsResult 메서드를 overriding하고 권한별로 분기하여 처리하면 된다.
    // 만약, 권한 요청 후 필요한 처리를 권한별로 나눠서 구현하고 싶다면 ActivityResultCallback을 사용

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 위치정보 권한 확인을 위한 객체 생성
        val r1 = ActivityResultContracts.RequestMultiplePermissions()
        val callback1 = LocationPermissionCallback()
        val locationLauncher = registerForActivityResult(r1, callback1)

        val r2 = ActivityResultContracts.RequestMultiplePermissions()
        val callback2 = ContractPermissionCallback()
        val contactLauncher = registerForActivityResult(r2, callback2)

        activityMainBinding.run {

            button.run {
                setOnClickListener {
                    // 권한 확인을 요청
                    requestPermissions(permissionList, 0)
                }
            }

            button2.run{
                setOnClickListener{
                    // 권한 요청
                    val a1 = arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                    locationLauncher.launch(a1)
                }
            }
            button3.run{
                setOnClickListener{
                    // 권한 요청
                    val a1 = arrayOf(
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_CONTACTS
                    )
                    contactLauncher.launch(a1)
                }
            }
        }
    }

    // requestPermission 메서드를 통해 권한을 요청하여 요청 작업이 끝나면 호출되는 메서드
    // 권한 확인 후 권한이 허용되거나 거부된 경우 작업할 내용이 있을경우 메소드 사용
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        activityMainBinding.run {
            textView.text = ""

            for (idx in 0 until permissions.size) {
                // 현재 번째의 권한 이름을 가져온다.
                val p1 = permissions[idx]
                // 권한 허용 여부 값을 가져온다.
                val g1 = grantResults[idx]

                when (p1) {
                    Manifest.permission.ACCESS_FINE_LOCATION -> {
                        if (g1 == PackageManager.PERMISSION_GRANTED) {
                            textView.append("위치 1 : 허용\n")
                        } else {
                            textView.append("위치 2 : 거부\n")
                        }
                    }

                    Manifest.permission.ACCESS_COARSE_LOCATION -> {
                        if (g1 == PackageManager.PERMISSION_GRANTED) {
                            textView.append("위치 2 : 허용\n")
                        } else {
                            textView.append("위치 2 : 거부\n")
                        }
                    }

                    Manifest.permission.READ_CONTACTS -> {
                        if (g1 == PackageManager.PERMISSION_GRANTED) {
                            textView.append("연락처 1 : 허용\n")
                        } else {
                            textView.append("연락처 1 : 거부\n")
                        }
                    }

                    Manifest.permission.WRITE_CONTACTS -> {
                        if (g1 == PackageManager.PERMISSION_GRANTED) {
                            textView.append("연락처 2 : 허용\n")
                        } else {
                            textView.append("연락처 2 : 거부\n")
                        }
                    }

                    Manifest.permission.READ_EXTERNAL_STORAGE -> {
                        if (g1 == PackageManager.PERMISSION_GRANTED) {
                            textView.append("저장소 1 : 허용\n")
                        } else {
                            textView.append("저장소 1 : 거부\n")
                        }
                    }

                    Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                        if (g1 == PackageManager.PERMISSION_GRANTED) {
                            textView.append("저장소 2 : 허용\n")
                        } else {
                            textView.append("저장소 2 : 거부\n")
                        }
                    }

                    Manifest.permission.INTERNET -> {
                        if (g1 == PackageManager.PERMISSION_GRANTED) {
                            textView.append("네트워크 : 허용\n")
                        } else {
                            textView.append("네트워크 : 거부\n")
                        }
                    }
                }
            }
        }
    }

    inner class LocationPermissionCallback : ActivityResultCallback<Map<String, Boolean>> {
        override fun onActivityResult(result: Map<String, Boolean>?) {
            activityMainBinding.run {
                textView.text = "위치 권한 확인\n"

                if (result != null) {
                    // 확인한 권한 만큼 반복
                    for (key in result.keys) {
                        when(key) {
                            Manifest.permission.ACCESS_FINE_LOCATION -> {
                                if (result[key] == true) {
                                    textView.append("위치1 권한 허용\n")
                                } else {
                                    textView.append("위치1 권한 거부\n")
                                }
                            }
                            Manifest.permission.ACCESS_COARSE_LOCATION ->{
                                if(result[key] == true){
                                    textView.append("위치2 권한 허용\n")
                                } else {
                                    textView.append("위치2 권한 거부\n")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    inner class ContractPermissionCallback : ActivityResultCallback<Map<String, Boolean>>{
        override fun onActivityResult(result: Map<String, Boolean>?) {

            activityMainBinding.run{
                textView.text = "연락처 권한 확인\n"

                if(result != null) {
                    for (permission in result.keys) {
                        when(permission){
                            Manifest.permission.READ_CONTACTS -> {
                                if(result[permission] == true){
                                    textView.append("연락처1 권한 허용\n")
                                } else {
                                    textView.append("연락처2 권한 거부\n")
                                }
                            }
                            Manifest.permission.WRITE_CONTACTS ->{
                                if(result[permission] == true){
                                    textView.append("연락처2 권한 허용\n")
                                } else {
                                    textView.append("연락처2 권한 거부\n")
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}