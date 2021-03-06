package com.autodhil.musicapps.views.edituser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.autodhil.musicapps.R
import com.autodhil.musicapps.databinding.ActivityEditUserBinding
import org.jetbrains.anko.toast

class EditUserActivity : AppCompatActivity() {
    private lateinit var editUserBinding: ActivityEditUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editUserBinding = ActivityEditUserBinding.inflate(layoutInflater)
        setContentView(editUserBinding.root)

        init()
        onClick()
    }


    private fun onClick() {
        editUserBinding.btnUpdate.setOnClickListener {
            toast("Berhasil di update")
        }

        editUserBinding.tbEditUser.setNavigationOnClickListener {
            finish()
        }
    }

    private fun init() {
        //Set Support ActionBar
        setSupportActionBar(editUserBinding.tbEditUser)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null
    }
}