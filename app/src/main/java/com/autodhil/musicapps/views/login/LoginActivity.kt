package com.autodhil.musicapps.views.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.autodhil.musicapps.views.main.MainActivity
import com.autodhil.musicapps.databinding.ActivityLoginBinding
import com.autodhil.musicapps.views.forgotpassword.ForgotPasswordActivity
import com.autodhil.musicapps.views.register.RegisterActivity
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        onClick()
    }

    private fun onClick() {
        loginBinding.btnForgotPassword.setOnClickListener {
            startActivity<ForgotPasswordActivity>()
        }

        loginBinding.btnNewRegister.setOnClickListener {
            startActivity<RegisterActivity>()
        }

        loginBinding.btnLogin.setOnClickListener {
            startActivity<MainActivity>()
        }
    }


}