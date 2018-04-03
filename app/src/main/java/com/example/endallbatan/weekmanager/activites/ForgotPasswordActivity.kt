package com.example.endallbatan.weekmanager.activites

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.endallbatan.weekmanager.R


class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)
        actionBarSetup()
    }
    private fun actionBarSetup() {
        title = "Reset your password"
    }
}
