package com.example.expiryreminderapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.NestedScrollView
import com.example.expiryreminderapp.helpers.InputValidation
import com.example.expiryreminderapp.modal.User
import com.example.expiryreminderapp.sql.DatabaseHelper
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private val activity = this@RegisterActivity

    private lateinit var ScrollView: NestedScrollView

    private lateinit var Namelayout: TextInputLayout
    private lateinit var Emaillayout: TextInputLayout
    private lateinit var Passwordlayout: TextInputLayout
    private lateinit var ConfirmPasswordlayout: TextInputLayout

    private lateinit var Name: TextInputEditText
    private lateinit var Emailid: TextInputEditText
    private lateinit var Password: TextInputEditText
    private lateinit var Confirmpassword: TextInputEditText

    private lateinit var ButtonRegister: AppCompatButton
    private lateinit var TextViewLogin: AppCompatTextView

    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



        // hiding the action bar
        supportActionBar!!.hide()

        // initializing the views
        initViews()

        // initializing the listeners
        initListeners()

        // initializing the objects
        initObjects()

    }
    private fun initViews() {
        ScrollView = findViewById(R.id.scrollview) as NestedScrollView

        Namelayout = findViewById(R.id.namelayout) as TextInputLayout
        Emaillayout = findViewById(R.id.emaillayout) as TextInputLayout
        Passwordlayout = findViewById(R.id.passwordlayout) as TextInputLayout
        ConfirmPasswordlayout = findViewById(R.id.confpasswordlayout) as TextInputLayout

        Name = findViewById(R.id.name) as TextInputEditText
        Emailid = findViewById(R.id.email_id) as TextInputEditText
        Password = findViewById(R.id.password) as TextInputEditText
        Confirmpassword = findViewById(R.id.confpassword) as TextInputEditText

        ButtonRegister = findViewById(R.id.register_Button) as AppCompatButton

        TextViewLogin = findViewById(R.id.logintextview) as AppCompatTextView

    }
    private fun initListeners() {
        ButtonRegister!!.setOnClickListener(this)
        TextViewLogin!!.setOnClickListener(this)

    }
    private fun initObjects() {
        inputValidation = InputValidation(activity)
        databaseHelper = DatabaseHelper(activity)


    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.register_Button -> postDataToSQLite()

            R.id.logintextview -> {

                val intentRegister = Intent(applicationContext, MainActivity::class.java)
                startActivity(intentRegister)

            }
        }
    }

    private fun postDataToSQLite() {
        if (!inputValidation!!.isInputEditTextFilled(Name, Namelayout, getString(R.string.error_message_name))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(Emailid, Emaillayout, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(Emailid, Emaillayout, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(Password, Passwordlayout, getString(R.string.error_message_password))) {
            return
        }
        if (!inputValidation!!.isInputEditTextMatches(Password, Confirmpassword,
                ConfirmPasswordlayout, getString(R.string.error_password_match))) {
            return
        }

        if (!databaseHelper!!.checkUser(Emailid!!.text.toString().trim())) {

            var user = User(name = Name!!.text.toString().trim(),
                email = Emailid!!.text.toString().trim(),
                password = Password!!.text.toString().trim())

            databaseHelper!!.addUser(user)

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(ScrollView!!, getString(R.string.success_message), Snackbar.LENGTH_LONG).show()
            emptyInputEditText()


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(ScrollView!!, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show()
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private fun emptyInputEditText() {
        Name!!.text = null
        Emailid!!.text = null
        Password!!.text = null
        Confirmpassword!!.text = null
    }


}