package com.autodhil.musicapps.views.detailalbum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.autodhil.musicapps.R
import com.autodhil.musicapps.adapter.SongsAlbumAdapter
import com.autodhil.musicapps.databinding.ActivityDetailAlbumBinding
import com.autodhil.musicapps.models.Album
import com.autodhil.musicapps.models.Song
import com.autodhil.musicapps.views.playsong.PlaySongActivity
import com.bumptech.glide.Glide
import hide
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import visible

class DetailAlbumActivity : AppCompatActivity() {

    companion object{
        const val KEY_ALBUM = "key_albums"
    }

    private lateinit var detailAlbumBinding: ActivityDetailAlbumBinding
    private lateinit var songsAlbumsAdapter: SongsAlbumAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_album)
        detailAlbumBinding = ActivityDetailAlbumBinding.inflate(layoutInflater)
        setContentView(detailAlbumBinding.root)

        init()
        showLoading()
        Handler(Looper.getMainLooper()).postDelayed({
            getData() }
            ,2000)

        onClick()
    }

    private fun getData() {
        if (intent != null){
            val album = intent.getParcelableExtra<Album>(KEY_ALBUM)
            if (album != null){
                hideLoading()
                initView(album)
            }else{
                hideLoading()
                toast("Data null")
            }
        }
    }

    private fun initView(album: Album) {
        detailAlbumBinding.tvNameAlbum.text = album.nameAlbum
        detailAlbumBinding.tvArtistAlbum.text = album.artistAlbum
        detailAlbumBinding.tvReleaseAlbum.text = album.getAlbum()
        Glide.with(this)
            .load(album.imageAlbum)
            .placeholder(android.R.color.darker_gray)
            .into(detailAlbumBinding.ivDetailAlbum)

        showSongsAlbum(album.songs)
    }

    private fun showSongsAlbum(songs: List<Song>?) {
        if (songs != null){
            songsAlbumsAdapter.setData(songs as MutableList<Song>)
            detailAlbumBinding.rvDetailAlbum.adapter = songsAlbumsAdapter
        }else{
            toast("Data Null")
        }

    }

    private fun onClick() {
        detailAlbumBinding.tbDetailAlbum.setNavigationOnClickListener {
            finish()
        }

        songsAlbumsAdapter.onClick { songs, position ->
              startActivity<PlaySongActivity>(
                PlaySongActivity.KEY_SONG to songs,
                PlaySongActivity.KEY_POSITION to position
            )
        }
    }

    private fun init() {
        //Set Support ActionBar
        setSupportActionBar(detailAlbumBinding.tbDetailAlbum)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null

        songsAlbumsAdapter = SongsAlbumAdapter()
    }

    private fun showLoading(){
        detailAlbumBinding.swipeDetailAlbum.visible()
    }

    private fun hideLoading(){
        detailAlbumBinding.swipeDetailAlbum.hide()
    }
}