package com.example.endallbatan.weekmanager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        actionBarSetup()
    }
    private fun actionBarSetup() {
        title = "Reset your password"
    }
}
