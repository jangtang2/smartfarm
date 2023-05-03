package com.example.smartfarm

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text1 = findViewById<TextView>(R.id.txt1)
        val text2 = findViewById<TextView>(R.id.txt2)
        val text3 = findViewById<TextView>(R.id.txt3)

        val btnon = findViewById<Button>(R.id.btn_on)
        val btnoff = findViewById<Button>(R.id.btn_off)

        //Write a message to the database
        //val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        //val myRef : DatabaseReference = database.getReference("message")

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef : DatabaseReference = database.getReference("esp8266")

        val msgRef: DatabaseReference = database.getReference("message")

        //Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //값이 변경된게 있으면 database의 값이 갱신되면 자동 호출된다.

                //val value = snapshot?.value
                //text1.text = "$value"

                val cds = snapshot.child("cds").value
                text1.text ="lux = " + cds.toString()+ "lux"
                val humi = snapshot.child("humi").value
                text2.text ="humi = " + humi.toString() + "%"
                val temp = snapshot.child("temp").value
                text3.text ="temp = " + temp.toString()+"'c"
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read value.")

            }
        })
        btnon.setOnClickListener{
            msgRef.setValue(1)
        }
        btnoff.setOnClickListener{
            msgRef.setValue(0)
        }
    }
}