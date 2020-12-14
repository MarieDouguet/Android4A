package com.esiea.android4a.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Button
import androidx.lifecycle.Observer
import com.esiea.android4a.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {


    val mainViewModel: MainViewModel by inject()


    fun getEmoji(unicode: Int): String {
        return String(Character.toChars(unicode))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_account = findViewById(R.id.create_account_button) as Button



        mainViewModel.loginLiveData.observe(this, Observer {
            when (it) {
                is LoginSucess -> {
                    //TODO Navigate
                }
                LoginError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle(Html.fromHtml("<b>"+getString(R.string.error)+"</b>"))
                        .setMessage("You have to create an account to continue ! " + getEmoji(0x1F60A))
                        .setPositiveButton("Create an account") { dialog, which ->
                            val intent = Intent(this, CreateAccount::class.java)
                            startActivity(intent)
                        }
                        .setNeutralButton("Cancel") { dialog, which ->
                            dialog.dismiss()
                        }
                        .show()

                }
            }

        })

        btn_account.setOnClickListener {
            val intent = Intent(this, CreateAccount::class.java)
            startActivity(intent)
        }

        login_button.setOnClickListener {
            mainViewModel.onClickedLogin(
                login_edit.text.toString().trim(),
                password_edit.text.toString()
            )
        }

    }
}