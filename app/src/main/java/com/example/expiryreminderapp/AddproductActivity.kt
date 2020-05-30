package com.example.expiryreminderapp

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.NestedScrollView
import com.example.expiryreminderapp.helpers.InputValidation
import com.example.expiryreminderapp.modal.Product
import com.example.expiryreminderapp.modal.User
import com.example.expiryreminderapp.sql.DatabaseHelper
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_addproduct.*
import java.util.*
import kotlin.text.category


class AddproductActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener ,View.OnClickListener {

    private val activity = this@AddproductActivity

    private lateinit var ScrollView: NestedScrollView
    private lateinit var Productnamelayout: TextInputLayout
    private lateinit var Expdatelayout: TextInputLayout

    private lateinit var Productname: TextInputEditText
    private lateinit var Productexpdate: TextInputEditText

    private lateinit var ProductradioGroup: RadioGroup
    private lateinit var Buttonaddproduct: AppCompatButton
    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper

    var products =
        arrayOf("Select", "Food", "Drugs", "Identy Card", "Licence", "Debit Card", "Credit Card")

    var spinner: AppCompatSpinner? = null
    var Category: AppCompatTextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addproduct)


        // initializing the views
        initViews()

        // initializing the listeners
        initListeners()

        // initializing the objects
        initObjects()

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
                val radio: AppCompatRadioButton = findViewById(checkedId)
                Toast.makeText(
                    applicationContext, " On checked change : ${radio.text}",
                    Toast.LENGTH_SHORT
                ).show()
            })

    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        Category!!.text = "Categories : "
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }

    fun radio_button_click(view: View) {
        // Get the clicked radio button instance
        val radio: AppCompatRadioButton = findViewById(radio_group.checkedRadioButtonId)
//        Toast.makeText(applicationContext,"On click : ${radio.text}",
//            Toast.LENGTH_SHORT).show()
    }

    private fun initViews() {

        ScrollView = findViewById(R.id.scrollview) as NestedScrollView
        Productnamelayout = findViewById(R.id.productnamelayout) as TextInputLayout
        Expdatelayout = findViewById(R.id.productexplayout) as TextInputLayout


        Productname = findViewById(R.id.productname) as TextInputEditText
        Productexpdate = findViewById(R.id.expirydate) as TextInputEditText

        spinner = findViewById(R.id.category_spinner) as AppCompatSpinner
        ProductradioGroup = findViewById(R.id.radio_group) as RadioGroup

        Buttonaddproduct = findViewById(R.id.addproduct) as AppCompatButton

    }

    private fun initListeners() {
        Buttonaddproduct!!.setOnClickListener(this)

    }

    private fun initObjects() {
        inputValidation = InputValidation(activity)
        databaseHelper = DatabaseHelper(activity)
    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.addproduct -> postDataToSQLite()

            R.id.addproduct -> {

                val intentRegister = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intentRegister)

            }
        }
    }

    private fun postDataToSQLite() {

        if (!inputValidation!!.isInputEditTextFilled(
                Productname,
                Productnamelayout,
                getString(R.string.error_message_productname)
            )
        ) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(
                Productexpdate,
                Expdatelayout,
                getString(R.string.error_message_expdate)
            )
        ) {
            return
        }

//        if (!databaseHelper!!.checkUser(Productname!!.text.toString().trim())) {
//
//            var product = Product(
//                productname = Productname!!.text.toString().trim(),
//                expirydate = Productexpdate!!.text.toString().trim(),
//
//
//
//            )
//
//            databaseHelper!!.addProduct(product)
//
//        }
    }

    private fun emptyInputEditText() {
        Productname!!.text = null
        Productexpdate!!.text = null

    }

}
