package com.autodhil.musicapps.views.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.autodhil.musicapps.R
import com.autodhil.musicapps.databinding.ActivityRegisterBinding
import org.jetbrains.anko.toast

class  RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        init()
        onClick()
    }

    private fun onClick() {
        registerBinding.tbRegister.setNavigationOnClickListener {
            finish()
        }

        registerBinding.btnAlreadyMemberLogin.setOnClickListener {
            finish()
        }

        registerBinding.btnRegister.setOnClickListener {
            toast("Register")
        }
    }

    private fun init() {
        //Setup Support Action Bar
        setSupportActionBar(registerBinding.tbRegister)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null
    }
}