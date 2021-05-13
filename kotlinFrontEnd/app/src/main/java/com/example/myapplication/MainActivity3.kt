package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {

    var restAPI = RestAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        btnLogin.setOnClickListener {

            var intent = Intent(this@MainActivity3, MainActivity::class.java)
            intent.putExtra("username", editLogin.text.toString())
            restAPI.username = editLogin.text.toString()
            startActivity(intent)

        }

    }
}