package com.esiea.android4a.presentation.main

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.esiea.android4a.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_create_account.*
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
        val btn_account: Button = findViewById(R.id.create_account_button)
        val btn_signIn: Button = findViewById(R.id.signIn_button)
        val login: EditText = findViewById(R.id.login_edit)


        val image: ImageView = findViewById(R.id.pokemon)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        image.startAnimation(slideAnimation)

        mainViewModel.loginLiveData.observe(this, Observer {
            when (it) {
                is LoginSuccess -> {
                    Toast.makeText(this, login.text, Toast.LENGTH_LONG).show()
                }
                LoginError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle(Html.fromHtml("<b>" + getString(R.string.error) + "</b>"))
                        .setMessage(
                            "You have to create an account to continue ! " + getEmoji(
                                0x1F60A
                            )
                        )
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

        /* btn_signIn.setOnClickListener {
                    val intent = Intent(this, PokemonListActivity::class.java)
                    startActivity(intent)
                }*/
        btn_account.setOnClickListener {
            val intent = Intent(this, CreateAccount::class.java)
            startActivity(intent)
        }
        btn_signIn.setOnClickListener {
            mainViewModel.onClickedLogin(
                login_edit.text.toString().trim(),
                password_edit.text.toString()
            )
        }

    }
}