package com.example.endallbatan.weekmanager

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainScreenActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
        val mUser = mFirebaseAuth!!.currentUser
        val userReference = mDatabaseReference!!.child(mUser!!.uid)
        val email = findViewById<View>(R.id.email) as TextView
        email.text = mUser.email
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.main_screen_activity)
        super.onCreate(savedInstanceState)
        initialise()
    }

    private fun initialise() {
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mFirebaseAuth = FirebaseAuth.getInstance()
        uiLogoutButton = findViewById<View>(R.id.logoutButton) as Button
        uiLogoutButton!!.setOnClickListener{(logout())}
    }

    private fun logout() {
        mFirebaseAuth!!.signOut()
        redirectToLoginActivity()
    }

    private fun redirectToLoginActivity() {
        val intent = Intent(this,StartScreenActivity::class.java)
        startActivity(intent)
    }

    private var uiLogoutButton:Button? = null
}

private var mDatabaseReference: DatabaseReference? = null
private var mDatabase: FirebaseDatabase? = null
private var mFirebaseAuth: FirebaseAuth? = null