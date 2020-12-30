package com.esiea.android4a.presentation.createAccount

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.esiea.android4a.R
import com.esiea.android4a.presentation.main.CreateError
import com.esiea.android4a.presentation.main.CreateSuccess
import com.esiea.android4a.presentation.login.MainActivity
import kotlinx.android.synthetic.main.activity_create_account.*
import org.koin.android.ext.android.inject

class CreateAccountActivity : AppCompatActivity() {

    val createAccountViewModel: CreateAccountViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle("Create Account")

        val btn_signUp: Button = findViewById(R.id.signUp_button)
        val btn_cancel: Button = findViewById(R.id.cancel_button)

        btn_signUp.setOnClickListener {
            val emailCreate = createEmail_edit.text.toString().trim()
            val passwordCreate = createPassword_edit.text.toString()

            when { //check if null
                emailCreate.isNullOrEmpty() && passwordCreate.isNullOrEmpty() -> {
                    createEmail_edit.error = getString(R.string.error_email)
                    createPassword_edit.error = getString(R.string.error_password)

                }
                emailCreate.isNullOrEmpty() -> {
                    createEmail_edit.error = getString(R.string.error_email)
                }
                passwordCreate.isNullOrEmpty() -> {
                    createPassword_edit.error = getString(R.string.error_password)
                }
                else -> {
                    createAccountViewModel.onClickedCreate(emailCreate, passwordCreate)
                }
            }

            createAccountViewModel.createLiveData.observe(this, Observer {
                when (it) {
                    is CreateSuccess -> {
                        Toast.makeText(this, "Account created", Toast.LENGTH_LONG).show()
                        Handler().postDelayed(
                            {
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            },
                            4000
                        )

                    }
                    CreateError -> {
                        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                    }
                }

            })
        }

        btn_cancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
