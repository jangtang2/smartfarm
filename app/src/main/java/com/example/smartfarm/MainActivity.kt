package com.example.smartfarm

import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.smartfarm.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val frame: RelativeLayout by lazy { // activity_main의 화면 부분
        findViewById(R.id.main_container)
    }
    private val bottomNagivationView: BottomNavigationView by lazy { // 하단 네비게이션 바
        findViewById(R.id.bottom_navigation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 애플리케이션 실행 후 첫 화면 설정
        supportFragmentManager.beginTransaction().add(frame.id, MonitorFragment()).commit()

        // 하단 네비게이션 바 클릭 이벤트 설정
        bottomNagivationView.setOnNavigationItemSelectedListener {item ->
            when(item.itemId) {
                R.id.navigation_monitor -> {
                    replaceFragment(MonitorFragment())
                    true
                }
                R.id.navigation_control -> {
                    replaceFragment(ControlFragment())
                    true
                }
                R.id.navigation_setting-> {
                    replaceFragment(SettingFragment())
                    true
                }
                else -> false
            }
        }

        //val text1 = findViewById<TextView>(R.id.txt1)
        //val text2 = findViewById<TextView>(R.id.txt2)
        //val text3 = findViewById<TextView>(R.id.txt3)

        //val btnon = findViewById<Button>(R.id.btn_on)
        //val btnoff = findViewById<Button>(R.id.btn_off)

        //Write a message to the database
        //val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        //val myRef : DatabaseReference = database.getReference("message")

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef : DatabaseReference = database.getReference("esp8266")

        //val msgRef: DatabaseReference = database.getReference("message")

        //Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //값이 변경된게 있으면 database의 값이 갱신되면 자동 호출된다.

                val bundle: Bundle = Bundle()
                val temp = snapshot.child("temp").value
                bundle.putString("temp", temp.toString()+"℃")
                val humi = snapshot.child("humi").value
                bundle.putString("humi", humi.toString() + "%")

                val monitorFragment: MonitorFragment = MonitorFragment()

                monitorFragment.arguments = bundle

                val manager : FragmentManager = supportFragmentManager

                val transaction: FragmentTransaction = manager.beginTransaction()

                transaction.replace(R.id.main_container, monitorFragment).commit()
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value.")

            }
        })

        /*btnon.setOnClickListener{
            msgRef.setValue(1)
        }
        btnoff.setOnClickListener{
            msgRef.setValue(0)
        }*/

    }

    // 화면 전환 구현 메소드
    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(frame.id, fragment).commit()
    }
}