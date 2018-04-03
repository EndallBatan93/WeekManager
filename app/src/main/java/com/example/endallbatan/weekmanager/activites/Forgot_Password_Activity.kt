package com.example.endallbatan.weekmanager.activites

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.endallbatan.weekmanager.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Forgot_Password_Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.forgot_password_screen)
        super.onCreate(savedInstanceState)
        initialise()
    }

    private fun initialise() {
        TAG = "Forgot_Password_Activity"
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mFirebaseAuth = FirebaseAuth.getInstance()
        uiPasswordField = findViewById<View>(R.id.emailField) as EditText
        uiResetButton = findViewById<View>(R.id.resetPasswordButton) as Button
        uiResetButton!!.setOnClickListener{(resetPassword())}
    }

    private fun resetPassword() {
        email = uiPasswordField!!.text.toString()
        if(!TextUtils.isEmpty(email)) {
            mFirebaseAuth!!.sendPasswordResetEmail(email!!)
                    .addOnCompleteListener(this) {task ->
                        if(task.isSuccessful) {
                            Toast.makeText(this@Forgot_Password_Activity,
                                    "Reset Link sent to:$email", Toast.LENGTH_SHORT).show()
                                    redirectUserToLoginActivity()
                        } else {
                            Log.d(TAG, "reset password failed", task.exception)
                            Toast.makeText(this@Forgot_Password_Activity,
                                    "Reset could not been sent", Toast.LENGTH_SHORT).show()
                        }
                    }
        }
    }

    private fun redirectUserToLoginActivity() {
        val intent = Intent(this, LoginToAccountActivity::class.java)
        startActivity(intent)
    }

    private var uiResetButton: Button? =  null
    private var uiPasswordField: EditText? = null

    private var email:String? = null
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mFirebaseAuth: FirebaseAuth? = null
    private var TAG: String? =  null
}