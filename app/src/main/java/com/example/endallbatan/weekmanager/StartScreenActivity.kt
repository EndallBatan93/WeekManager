package com.example.endallbatan.weekmanager

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class StartScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)
        actionBarSetup()
        val registerButton = findViewById<View>(R.id.registerButton) as Button
        registerButton.setOnClickListener{redirectToCreateAccountActivity()}

        val loginButton = findViewById<View>(R.id.loginButton) as Button
        loginButton.setOnClickListener {redirectToLoginActivity()}
    }

    private fun redirectToCreateAccountActivity() {
        val intent = Intent(this,CreateAccountActivity::class.java)
        startActivity(intent)
    }

    private fun redirectToLoginActivity() {
        val intent = Intent(this,LoginToAccountActivity::class.java)
        startActivity(intent)
    }

    private fun actionBarSetup() {
        title = "Welcome to WeekPlanner"
    }
}
