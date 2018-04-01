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

class LoginToAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)
        actionBarSetup()
        initialise()
    }
    private fun actionBarSetup() {
        title = "Login"
    }




    private fun initialise() {
        uiEmail = findViewById<View>(R.id.email) as EditText
        uiPassword = findViewById<View>(R.id.password) as EditText
        facebookLogin = findViewById<View>(R.id.facebookLogin) as Button
        googleLogin = findViewById<View>(R.id.googleLogin) as Button
        loginButton = findViewById<View>(R.id.loginButton) as Button
        forgotPassword     = findViewById<View>(R.id.resetPasswordButton) as Button
        mDatabase = FirebaseDatabase.getInstance()
        // Users in name of table in Database
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mFirebaseAuth = FirebaseAuth.getInstance()

        loginButton!!.setOnClickListener{(loginToAccount())}
        forgotPassword!!.setOnClickListener{(redirectToForgotPasswordActivity())}
    }

    private fun redirectToForgotPasswordActivity() {
        val intent = Intent(this, Forgot_Password_Activity::class.java)
        startActivity(intent)
    }

    private fun loginToAccount() {
        email = uiEmail?.text.toString()
        password = uiPassword?.text.toString()
        validateInputStringsNotEmpty(email,password)

        mFirebaseAuth!!.signInWithEmailAndPassword(email!!,password!!)
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful) {
                        Log.d(TAG,"signInWithEmailAndPassword:success")
                        redirectUserToMainActivity()
                    } else {
                        Log.w(TAG, "signInWithEmailAndPassword:failed", task.exception)
                        Toast.makeText(this@LoginToAccountActivity,
                                "Authentication failed",Toast.LENGTH_SHORT).show()
                    }
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

    //User Interface Elements
    private var forgotPassword: Button? = null
    private var uiEmail: EditText? = null
    private var uiPassword: EditText? = null
    private var googleLogin: Button? = null
    private var facebookLogin: Button? = null
    private var loginButton: Button? = null

    //InputValues
    private var email: String? = null
    private var password: String? = null
    //Firebase Stuff
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mFirebaseAuth: FirebaseAuth? = null

    //Variables
    private val TAG = "LoginToAccountActivity"
    //CheckVariables
    private var inputsNotEmpty: String? = null

}
