package com.example.expiryreminderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.expiryreminderapp.MainActivity
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var intent = Intent()

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            intent.setClass(this, AddproductActivity::class.java )
            startActivity(intent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_Expiry_history -> {
                Toast.makeText(applicationContext, "click on history", Toast.LENGTH_LONG).show()
                true
            }
            R.id.menu_Logout ->{

                intent.setClass(this, MainActivity::class.java )
                startActivity(intent)


                Toast.makeText(applicationContext, "logout", Toast.LENGTH_LONG).show()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

}
