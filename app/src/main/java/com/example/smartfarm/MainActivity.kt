package com.example.smartfarm

import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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

    var fragment_num = 1

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
                    fragment_num=1
                    true
                }
                R.id.navigation_control -> {
                    replaceFragment(ControlFragment())
                    fragment_num=2
                    true
                }
                R.id.navigation_setting-> {
                    replaceFragment(SettingFragment())
                    fragment_num=3
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
        val TH_Ref : DatabaseReference = database.getReference("")
        val TH_avg_Ref : DatabaseReference = database.getReference("avg_hour")

        //val msgRef: DatabaseReference = database.getReference("message")

        //Read from the database
        TH_Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //값이 변경된게 있으면 database의 값이 갱신되면 자동 호출된다.

                val bundle: Bundle = Bundle()
                val temp = snapshot.child("/esp8266/temp").value
                bundle.putString("temp", temp.toString()+"℃")
                val humi = snapshot.child("/esp8266/humi").value
                bundle.putString("humi", humi.toString() + "%")

                var atemp1 = snapshot.child("avg_hour/1/avg_t").value.toString()
                var atemp2 = snapshot.child("avg_hour/2/avg_t").value.toString()
                var atemp3 = snapshot.child("avg_hour/3/avg_t").value.toString()
                var atemp4 = snapshot.child("avg_hour/4/avg_t").value.toString()
                var atemp5 = snapshot.child("avg_hour/5/avg_t").value.toString()
                var atemp6 = snapshot.child("avg_hour/6/avg_t").value.toString()
                var atemp7 = snapshot.child("avg_hour/7/avg_t").value.toString()
                var atemp8 = snapshot.child("avg_hour/8/avg_t").value.toString()
                var atemp9 = snapshot.child("avg_hour/9/avg_t").value.toString()
                var atemp10 = snapshot.child("avg_hour/10/avg_t").value.toString()
                var atemp11 = snapshot.child("avg_hour/11/avg_t").value.toString()
                var atemp12 = snapshot.child("avg_hour/12/avg_t").value.toString()

                bundle.putString("atemp1", atemp1)
                bundle.putString("atemp2", atemp2)
                bundle.putString("atemp3", atemp3)
                bundle.putString("atemp4", atemp4)
                bundle.putString("atemp5", atemp5)
                bundle.putString("atemp6", atemp6)
                bundle.putString("atemp7", atemp7)
                bundle.putString("atemp8", atemp8)
                bundle.putString("atemp9", atemp9)
                bundle.putString("atemp10", atemp10)
                bundle.putString("atemp11", atemp11)
                bundle.putString("atemp12", atemp12)

                var ahumi1 = snapshot.child("avg_hour/1/avg_h").value.toString()
                var ahumi2 = snapshot.child("avg_hour/2/avg_h").value.toString()
                var ahumi3 = snapshot.child("avg_hour/3/avg_h").value.toString()
                var ahumi4 = snapshot.child("avg_hour/4/avg_h").value.toString()
                var ahumi5 = snapshot.child("avg_hour/5/avg_h").value.toString()
                var ahumi6 = snapshot.child("avg_hour/6/avg_h").value.toString()
                var ahumi7 = snapshot.child("avg_hour/7/avg_h").value.toString()
                var ahumi8 = snapshot.child("avg_hour/8/avg_h").value.toString()
                var ahumi9 = snapshot.child("avg_hour/9/avg_h").value.toString()
                var ahumi10 = snapshot.child("avg_hour/10/avg_h").value.toString()
                var ahumi11 = snapshot.child("avg_hour/11/avg_h").value.toString()
                var ahumi12 = snapshot.child("avg_hour/12/avg_h").value.toString()

                bundle.putString("ahumi1", ahumi1)
                bundle.putString("ahumi2", ahumi2)
                bundle.putString("ahumi3", ahumi3)
                bundle.putString("ahumi4", ahumi4)
                bundle.putString("ahumi5", ahumi5)
                bundle.putString("ahumi6", ahumi6)
                bundle.putString("ahumi7", ahumi7)
                bundle.putString("ahumi8", ahumi8)
                bundle.putString("ahumi9", ahumi9)
                bundle.putString("ahumi10", ahumi10)
                bundle.putString("ahumi11", ahumi11)
                bundle.putString("ahumi12", ahumi12)


                val monitorFragment: MonitorFragment = MonitorFragment()

                monitorFragment.arguments = bundle

                val manager : FragmentManager = supportFragmentManager

                val transaction: FragmentTransaction = manager.beginTransaction()
                if(fragment_num==1) {
                    transaction.replace(R.id.main_container, monitorFragment).commit()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value.")

            }
        })

        /*TH_avg_Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //값이 변경된게 있으면 database의 값이 갱신되면 자동 호출된다.

                val bundle: Bundle = Bundle()
                var atemp = snapshot.child("avg_hour/1/avg_t").value
                var avtemp = atemp.toString()
                bundle.putString("atemp", avtemp)

                val monitorFragment: MonitorFragment = MonitorFragment()

                monitorFragment.arguments = bundle

                val manager : FragmentManager = supportFragmentManager

                val transaction: FragmentTransaction = manager.beginTransaction()
                if(fragment_num==1) {
                    transaction.replace(R.id.main_container, monitorFragment).commit()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value.")

            }
        })*/

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