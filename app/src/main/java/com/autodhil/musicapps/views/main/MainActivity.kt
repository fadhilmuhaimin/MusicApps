package com.autodhil.musicapps.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.autodhil.musicapps.R
import com.autodhil.musicapps.databinding.ActivityMainBinding
import com.autodhil.musicapps.views.mytracks.MyTracksFragment
import com.autodhil.musicapps.views.topalbums.TopAlbumsFragment
import com.autodhil.musicapps.views.topcharts.TopChartsFragment
import com.autodhil.musicapps.views.user.UserFragment

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        init()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val selectedItemId = mainBinding.btmNavigationMain.getSelectedItemId()
        if (selectedItemId == R.id.action_top_charts) {
            finishAffinity()
        } else {
            openHomeFragment()
        }
    }

    private fun init() {
        //Setup BottomNavigation Bar
        mainBinding.btmNavigationMain.setOnItemSelectedListener { id ->
            when (id) {
                R.id.action_top_charts -> openFragment(TopChartsFragment())
                R.id.action_my_tracks -> openFragment(MyTracksFragment())
                R.id.action_top_albums -> openFragment(TopAlbumsFragment())
                R.id.action_user -> openFragment(UserFragment())
            }
        }
        openHomeFragment()
    }

    private fun openHomeFragment() {
        mainBinding.btmNavigationMain.setItemSelected(R.id.action_top_charts)
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_main, fragment)
            .addToBackStack(null)
            .commit()
    }

}