package com.example.expiryreminderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var Email_id = findViewById(R.id.email_id) as EditText
        var Password = findViewById(R.id.password) as EditText
        var btn_login = findViewById(R.id.login_Button) as Button


        var db= Room.databaseBuilder(applicationContext,LoginDb::class.java, "ExpiryDB").build()

        register_textview.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        login_Button.setOnClickListener{



            val intent = Intent(this, HomeActivity::class.java )
            startActivity(intent)

        }
    }
}
