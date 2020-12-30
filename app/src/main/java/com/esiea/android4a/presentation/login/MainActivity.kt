package com.esiea.android4a.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.esiea.android4a.R
import com.esiea.android4a.presentation.createAccount.CreateAccountActivity
import com.esiea.android4a.presentation.main.LoginError
import com.esiea.android4a.presentation.main.LoginSuccess
import com.esiea.android4a.presentation.recyclerView.PokemonListActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        val password: EditText = findViewById(R.id.password_edit)

        val image: ImageView = findViewById(R.id.pokemon)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        image.startAnimation(slideAnimation)

        mainViewModel.loginLiveData.observe(this, Observer {
            when (it) {
                is LoginSuccess -> {
                    val intent = Intent(this, PokemonListActivity::class.java)
                    startActivity(intent)
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
                            val intent = Intent(this, CreateAccountActivity::class.java)
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
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
        btn_signIn.setOnClickListener {
            mainViewModel.onClickedLogin(
                login.text.toString().trim(),
                password.text.toString()
            )
        }
    }
}