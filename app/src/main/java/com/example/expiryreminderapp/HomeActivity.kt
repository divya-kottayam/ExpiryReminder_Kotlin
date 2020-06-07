package com.example.expiryreminderapp

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expiryreminderapp.MainActivity
import com.example.expiryreminderapp.modal.Product
import com.example.expiryreminderapp.sql.DatabaseHelper
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {

    private val activity = this@HomeActivity
    private lateinit var textViewName: AppCompatTextView
    private lateinit var recyclerViewProducts: RecyclerView
    private lateinit var listProducts: MutableList<Product>
    private lateinit var productsRecyclerAdapter: ProductRecyclerAdapter
    private lateinit var databaseHelper: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()
        initObjects()

        var intent = Intent()

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            intent.setClass(this, AddproductActivity::class.java )
            startActivity(intent)
        }
    }

    private fun initViews() {
//        textViewName = findViewById(R.id.proname) as AppCompatTextView
        recyclerViewProducts = findViewById(R.id.pro_recyclerview) as RecyclerView
    }

    private fun initObjects() {
        listProducts = ArrayList()
        productsRecyclerAdapter = ProductRecyclerAdapter(listProducts)

        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerViewProducts.layoutManager = mLayoutManager
        recyclerViewProducts.itemAnimator = DefaultItemAnimator()
        recyclerViewProducts.setHasFixedSize(true)
        recyclerViewProducts.adapter = productsRecyclerAdapter
        databaseHelper = DatabaseHelper(activity)

//        val emailFromIntent = intent.getStringExtra("EMAIL")
//        textViewName.text = emailFromIntent

        var getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()

    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.menu_Logout ->{

                intent.setClass(this, MainActivity::class.java )
                startActivity(intent)


                Toast.makeText(applicationContext, "logout", Toast.LENGTH_LONG).show()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

    inner class GetDataFromSQLite(): AsyncTask<Void, Void, List<Product>>()
    {



        override fun doInBackground(vararg p0: Void?): List<Product> {
            return databaseHelper.getAllProduct()
        }

        override fun onPostExecute(result: List<Product>?) {
            super.onPostExecute(result)
            listProducts.clear()
            listProducts.addAll(result!!)
            productsRecyclerAdapter = ProductRecyclerAdapter(listProducts)
            recyclerViewProducts.adapter = productsRecyclerAdapter
            productsRecyclerAdapter.notifyDataSetChanged()
        }
    }


}
