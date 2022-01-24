package com.autodhil.musicapps.views.splash

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.autodhil.musicapps.R
import com.autodhil.musicapps.repository.Repository
import com.autodhil.musicapps.views.login.LoginActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        val albums = Repository.getDataTopAlbumsFromAssets(this)
//        val topCharts = Repository.getDataTopChartsFromAssets(this)
//        Repository.addDataToTopCharts()
        delayAndGoToLongin()
//        Log.d(TAG, "onCreate: albums: ${albums?.size}, topCharts : ${topCharts?.size}")

    }

    private fun delayAndGoToLongin(){
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity<LoginActivity>()
            finishAffinity()
        },1200)
    }
}