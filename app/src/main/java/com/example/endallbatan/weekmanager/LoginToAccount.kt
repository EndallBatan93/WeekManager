package com.example.endallbatan.weekmanager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class LoginToAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)
        actionBarSetup()
    }
    private fun actionBarSetup() {
        title = "Login"
    }
}
