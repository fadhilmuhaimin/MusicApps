package com.autodhil.musicapps.views.changepassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.autodhil.musicapps.R
import com.autodhil.musicapps.databinding.ActivityChangePasswordBinding
import org.jetbrains.anko.toast

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var changePasswordBinding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changePasswordBinding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(changePasswordBinding.root)

        init()
        onClick()
    }

    private fun onClick() {
        changePasswordBinding.tbChangePassword.setNavigationOnClickListener {
            finish()
        }

        changePasswordBinding.btnUpdate.setOnClickListener {
            toast("Berhasil")
        }
    }

    private fun init() {
        //Set Support ActionBar
        setSupportActionBar(changePasswordBinding.tbChangePassword)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null
    }
}