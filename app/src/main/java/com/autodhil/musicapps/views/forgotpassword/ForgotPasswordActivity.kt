package com.autodhil.musicapps.views.forgotpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.autodhil.musicapps.databinding.ActivityChangePasswordBinding
import com.autodhil.musicapps.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var forgotPasswordbinding : ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forgotPasswordbinding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(forgotPasswordbinding.root)
        init()
    }

    private fun onClick(){
        forgotPasswordbinding.tbForgotPassword.setNavigationOnClickListener {
            finish()
        }

        forgotPasswordbinding.btnForgotPassword.setOnClickListener {

        }
    }

    private fun init() {
        //Set Support ActionBar
        setSupportActionBar(forgotPasswordbinding.tbForgotPassword)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null
    }
}
