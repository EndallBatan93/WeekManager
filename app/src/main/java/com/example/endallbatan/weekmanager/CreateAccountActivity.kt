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
        setContentView(R.layout.create_account)
        actionBarSetup()
        initialise()
    }

    private fun actionBarSetup() {
        title = "Create your Account"
    }

    private fun initialise() {
        uiUserName = findViewById<View>(R.id.username) as EditText
        uiEmail = findViewById<View>(R.id.email) as EditText
        uipassword = findViewById<View>(R.id.password) as EditText
        btnGoogleLogin = findViewById<View>(R.id.googleLogin) as Button
        btnFacebookLoging = findViewById<View>(R.id.facebookLogin) as Button
        btnCreateAccountActivity = findViewById<View>(R.id.createAccountButton) as Button
        mDatabase = FirebaseDatabase.getInstance()
        // Users in name of table in Database
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mFirebaseAuth = FirebaseAuth.getInstance()

        // !! means non null
        btnCreateAccountActivity!!.setOnClickListener { (createNewAccount()) }
    }

    private fun createNewAccount() {
        //Getting the strings in the texfields | ? means safe call
        username = uiUserName?.text.toString()
        email = uiEmail?.text.toString()
        password = uipassword?.text.toString()


        // only if all fields are filled
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            mFirebaseAuth!!
                    .createUserWithEmailAndPassword(email!!, password!!)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            //Sign in
                            Log.d(TAG, "createUserWithEmail:success!")
                            //creating userid with uuid
                            val userid = mFirebaseAuth!!.currentUser!!.uid

//                        verifyEmail()

                            val currenUserDb = mDatabaseReference!!.child(userid)
                            currenUserDb.child("username").setValue(username)

                        redirectUserToMainActivity()
                        } else {
                            Log.w(TAG,"createUserWithEmailAdress: failure", task.exception)
                            Toast.makeText(this@CreateAccountActivity,
                                    "Authentication failed", Toast.LENGTH_SHORT).show()
                        }
                    }
        } else { // error case when fields are empty
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_LONG).show()
        }
    }

    private fun redirectUserToMainActivity() {
        val intent = Intent(this,MainScreenActivity::class.java)
        startActivity(intent)
   }

    //UserInterface Elements
    private var uiUserName: EditText? = null
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

    private var username: String? = null
    private var email: String? = null
    private var password: String? = null
}
