package com.example.expiryreminderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_addproduct.*
import java.util.Arrays
import kotlin.text.category


class AddproductActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {

    var products = arrayOf("Select","Food", "Drugs", "Identy Card", "Licence", "Debit Card", "Credit Card")

    var spinner:Spinner? = null
    var Category: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addproduct)

        Category = this.category

        spinner = this.category_spinner
        spinner!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, products)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner!!.setAdapter(aa)

        radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                Toast.makeText(applicationContext," On checked change : ${radio.text}",
                    Toast.LENGTH_SHORT).show()
            })

    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        Category!!.text = "Categories : "
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }

    fun radio_button_click(view: View){
        // Get the clicked radio button instance
        val radio: RadioButton = findViewById(radio_group.checkedRadioButtonId)
//        Toast.makeText(applicationContext,"On click : ${radio.text}",
//            Toast.LENGTH_SHORT).show()
    }
}
