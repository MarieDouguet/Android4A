package com.esiea.android4a.presentation.main

import android.app.ActionBar
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContentProviderCompat.requireContext
import com.esiea.android4a.R
import kotlinx.android.synthetic.main.activity_create_account.*
import java.util.*

class CreateAccount : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle("Create Account")

        val btn_signUp : Button = findViewById(R.id.signUp_button)
        val btn_cancel : Button = findViewById(R.id.cancel_button)

        btn_cancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
