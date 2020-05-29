package com.example.expiryreminderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.NestedScrollView
import com.example.expiryreminderapp.helpers.InputValidation
import com.example.expiryreminderapp.sql.DatabaseHelper
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.E


class MainActivity : AppCompatActivity() , View.OnClickListener {



    private val activity = this@MainActivity

    private lateinit var ScrollView: NestedScrollView

    private lateinit var Emailid: TextInputEditText

    private lateinit var Emailidlayout: TextInputLayout

    private lateinit var Password: TextInputEditText

    private lateinit var Passwordlayout: TextInputLayout

    private lateinit var Loginbutton: AppCompatButton

    private lateinit var Logintext: AppCompatTextView

    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        // initializing the views
        initViews()

        // initializing the listeners
        initListeners()

        // initializing the objects
        initObjects()


//
//        register_textview.setOnClickListener {
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//        }
//
//        login_Button.setOnClickListener{
//
//            val intent = Intent(this, HomeActivity::class.java )
//            startActivity(intent)
//
//        }
    }
    private fun initViews() {

        ScrollView = findViewById(R.id.scrollview) as NestedScrollView


        Emailid = findViewById(R.id.email_id) as TextInputEditText

        Emailidlayout = findViewById(R.id.emaillayout) as TextInputLayout

        Password = findViewById(R.id.password) as TextInputEditText

        Passwordlayout = findViewById(R.id.passwordlayout) as TextInputLayout

        Loginbutton = findViewById(R.id.login_Button) as AppCompatButton

        Logintext = findViewById(R.id.register_textview) as AppCompatTextView




    }
    private fun initListeners() {

        Loginbutton!!.setOnClickListener(this)
        Logintext!!.setOnClickListener(this)
    }

    private fun initObjects() {

        databaseHelper = DatabaseHelper(activity)
        inputValidation = InputValidation(activity)

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.login_Button -> verifyFromSQLite()
            R.id.register_textview -> {
                // Navigate to RegisterActivity
                val intentRegister = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intentRegister)

            }
            R.id.login_Button ->{
                val intentRegister = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intentRegister)

            }
        }
    }
    private fun verifyFromSQLite() {

        if (!inputValidation!!.isInputEditTextFilled(Emailid!!,Emailidlayout!!,getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(Emailid!!,Emailidlayout!!,getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(Password!!,Passwordlayout!!,getString(R.string.error_message_email))) {
            return
        }

        if (databaseHelper!!.checkUser(Emailid!!.text.toString().trim { it <= ' ' }, Password!!.text.toString().trim { it <= ' ' })) {


            val accountsIntent = Intent(activity, HomeActivity::class.java)
            accountsIntent.putExtra("EMAIL", Emailid!!.text.toString().trim { it <= ' ' })
            emptyInputEditText()
            startActivity(accountsIntent)


        } else {

            // Snack Bar to show success message that record is wrong
            Snackbar.make(ScrollView!!, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show()
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private fun emptyInputEditText() {
        Emailid!!.text = null
        Password!!.text = null
    }
    }


