package com.example.endallbatan.weekmanager

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.login_screen.*

class LoginToAccount : AppCompatActivity() {
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

        mDatabase = FirebaseDatabase.getInstance()
        // Users in name of table in Database
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mFirebaseAuth = FirebaseAuth.getInstance()

        loginButton!!.setOnClickListener{(loginToAccount())}
    }

    private fun loginToAccount() {
        email = uiEmail?.text.toString()
        password = uiPassword?.text.toString()
    }

    //User Interface Elements
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
}
