package com.example.endallbatan.weekmanager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class CreateAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_account)
        actionBarSetup()
    }

    private fun actionBarSetup() {
        title = "Create your Account"
    }
}
