package com.example.endallbatan.weekmanager.activites

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.example.endallbatan.weekmanager.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainScreenActivity : AppCompatActivity() {

    override fun onBackPressed() {}

    override fun onStart() {
        super.onStart()
        val mUser = mFirebaseAuth!!.currentUser
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.main_screen_activity)
        super.onCreate(savedInstanceState)
        actionBarSetup()
        initialise()
    }

    private fun actionBarSetup() {
        title = "Your Week"
    }



    private fun initialise() {
        monday = findViewById<View>(R.id.monday) as Button
        tuesday = findViewById<View>(R.id.tuesday) as Button
        wednesday = findViewById<View>(R.id.wednesday) as Button
        thursday = findViewById<View>(R.id.thursday) as Button
        friday = findViewById<View>(R.id.friday) as Button
        saturday = findViewById<View>(R.id.saturday) as Button
        sunday = findViewById<View>(R.id.sunday) as Button


        monday!!.setBackgroundColor(1)

        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mFirebaseAuth = FirebaseAuth.getInstance()
        uiLogoutButton = findViewById<View>(R.id.logoutButton) as Button
        uiLogoutButton!!.setOnClickListener{(logout())}

        monday!!.setOnClickListener{(goTodDayView("monday"))}
        tuesday!!.setOnClickListener{(goTodDayView("tuesday"))}
        wednesday!!.setOnClickListener{(goTodDayView("wednesday"))}
        thursday!!.setOnClickListener{(goTodDayView("thursday"))}
        friday!!.setOnClickListener{(goTodDayView("friday"))}
        saturday!!.setOnClickListener{(goTodDayView("saturday"))}
        sunday!!.setOnClickListener{(goTodDayView("sunday"))}
    }

    private fun goTodDayView(weekday: String) {
        val intent = Intent(this, TasksForDayActivity::class.java)
        startActivity(intent)
    }

    private fun logout() {
        mFirebaseAuth!!.signOut()
        redirectToLoginActivity()
    }

    private fun redirectToLoginActivity() {
        val intent = Intent(this, StartScreenActivity::class.java)
        startActivity(intent)
    }

    private var uiLogoutButton:Button? = null
    private var monday:Button? = null
    private var tuesday:Button? = null
    private var wednesday:Button? = null
    private var thursday:Button? = null
    private var friday:Button? = null
    private var saturday:Button? = null
    private var sunday:Button? = null
}

private var mDatabaseReference: DatabaseReference? = null
private var mDatabase: FirebaseDatabase? = null
private var mFirebaseAuth: FirebaseAuth? = null
