package com.example.endallbatan.weekmanager

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class CreateAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_account_screen)
        actionBarSetup()
        initialise()
    }

    private fun actionBarSetup() {
        title = "Create your Account"
    }

    private fun initialise() {
        uiEmail = findViewById<View>(R.id.email) as EditText
        uipassword = findViewById<View>(R.id.password) as EditText
        btnGoogleLogin = findViewById<View>(R.id.googleLogin) as Button
        btnFacebookLoging = findViewById<View>(R.id.facebookLogin) as Button
        btnCreateAccountActivity = findViewById<View>(R.id.loginButton) as Button
        mDatabase = FirebaseDatabase.getInstance()
        // Users in name of table in Database
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mFirebaseAuth = FirebaseAuth.getInstance()

        // !! means non null
        btnCreateAccountActivity!!.setOnClickListener { (createNewAccount()) }
    }

    private fun createNewAccount() {
        //Getting the strings in the texfields | ? means safe call
        email = uiEmail?.text.toString()
        password = uipassword?.text.toString()

        validateInputStringsNotEmpty(email, password);

        if (inputsNotEmpty.equals("true")) {
            validateEmailAdress(email)
            checkPasswordSafety(password)
            startRegisteringAtFirebaseDatabase()
        }

    }

    private fun startRegisteringAtFirebaseDatabase() {
        if (emailIsValid.equals("true")&& passwordIsValid.equals("true")) {
            mFirebaseAuth!!
                    .createUserWithEmailAndPassword(email!!, password!!)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            //Sign in
                            Log.d(TAG, "createUserWithEmail:success!")
                            //creating userid with uuid
                            val userid = mFirebaseAuth!!.currentUser!!.uid
                            verifyEmail()
                            redirectUserToMainActivity()
                        } else {
                            Log.w(TAG, "createUserWithEmailAdress: failure", task.exception)
                            Log.i(TAG, task.exception.toString())
                            Toast.makeText(this@CreateAccountActivity,
                                    "Authentication failed", Toast.LENGTH_SHORT).show()
                        }
                    }
        }
    }

    private fun verifyEmail() {
            val mUser = mFirebaseAuth!!.currentUser
            mUser!!.sendEmailVerification()
                    .addOnCompleteListener(this) { task ->

                        if(task.isSuccessful) {
                            Toast.makeText(this@CreateAccountActivity,
                                    "Verfification Email sent to " + mUser.email,Toast.LENGTH_SHORT).show()
                        } else {
                            Log.d(TAG,"sending verification email failed:",task.exception)
                            Toast.makeText(this@CreateAccountActivity,
                                    "Sending verification email failed",Toast.LENGTH_SHORT).show()
                        }
                    }
    }
    private fun validateEmailAdress(email: String?) {
        val emailPattern = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
        if (emailPattern.containsMatchIn(email.toString())) {
            Log.i(TAG, "email is valid")
            emailIsValid = "true"
        } else {
            Toast.makeText(this@CreateAccountActivity,
                    "Email adress is not valid", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPasswordSafety(password: String?) {
        if(password!!.length < 6) {
            Toast.makeText(this@CreateAccountActivity,
                    "password is to short. At least 6 characters",Toast.LENGTH_SHORT).show()
        } else {
            passwordIsValid = "true"
        }
    }

    private fun validateInputStringsNotEmpty(email: String?, password: String?) {
        inputsNotEmpty = if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            "true"
        } else {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_LONG).show()
            Log.i(TAG,"wether username , email or password was empty")
            "false"
        }
    }

    private fun redirectUserToMainActivity() {
        val intent = Intent(this, MainScreenActivity::class.java)
        startActivity(intent)
    }

    //UserInterface Elements
    private var uiEmail: EditText? = null
    private var uipassword: EditText? = null
    private var btnCreateAccountActivity: Button? = null
    private var btnGoogleLogin: Button? = null
    private var btnFacebookLoging: Button? = null
    //Firebase Stuff
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mFirebaseAuth: FirebaseAuth? = null


    //Variables
    private val TAG = "CreateAccountActivity"

    private var email: String? = null
    private var password: String? = null

    // CheckVariables
    private var emailIsValid: String? =  null
    private var passwordIsValid: String? =  null
    private var inputsNotEmpty: String? = null
}
