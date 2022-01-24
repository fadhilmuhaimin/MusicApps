package com.autodhil.musicapps.views.playsong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.autodhil.musicapps.R
import com.autodhil.musicapps.databinding.ActivityPlaySongBinding
import com.autodhil.musicapps.models.Song
import com.bumptech.glide.Glide

class PlaySongActivity : AppCompatActivity() {

    companion object{
        const val KEY_SONG = "key_song"
        const val KEY_POSITION = "key_position"
    }

    private var position = 0

    private lateinit var playSongBinding: ActivityPlaySongBinding
    private var songs : MutableList<Song>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playSongBinding = ActivityPlaySongBinding.inflate(layoutInflater)
        setContentView(playSongBinding.root)

        init()
        getData()
        onClick()
    }

    private fun getData() {
        if (intent != null){
            songs = intent.getParcelableArrayListExtra(KEY_SONG)
            position = intent.getIntExtra(KEY_POSITION,0)
            songs?.let {
                val song = it[position]
                initVIew(song)
            }
        }


    }

    private fun initVIew(song: Song) {
        playSongBinding.tvNameSong.text = song.nameSong
        playSongBinding.tvArtistName.text = song.artistSong
        Glide.with(this)
            .load(song.imageSong)
            .placeholder(android.R.color.darker_gray)
            .into((playSongBinding.ivPlaySong))


    }

    private fun onClick() {
        playSongBinding.tbPlaySong.setNavigationOnClickListener {
            finish()
        }

        playSongBinding.btnPrevSong.setOnClickListener {
            playPrevSong()

        }

        playSongBinding.btnPlaySong.setOnClickListener {

        }

        playSongBinding.btnNextSong.setOnClickListener {
            playNextSong()

        }

        playSongBinding.btnAddTrack.setOnClickListener {

        }
    }

    private fun playNextSong() {
        val songSize = songs?.size?.minus(1)
        if (position < songSize!!){
            position += 1
            val song = songs?.get(position)
            if (song != null){
                initVIew(song)
            }
        }


    }

    private fun playPrevSong() {
        if (position > 0){
            position -= 1
            val song = songs?.get(position)
            if (song != null){
                initVIew(song)
            }
        }


    }

    private fun init() {
        //Set Support ActionBar
        setSupportActionBar(playSongBinding.tbPlaySong)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null
    }
}