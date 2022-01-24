package com.autodhil.musicapps.views.topalbums

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.autodhil.musicapps.adapter.MyTracksAdapter
import com.autodhil.musicapps.adapter.TopAlbumsAdapter
import com.autodhil.musicapps.databinding.FragmentTopAlbumsBinding
import com.autodhil.musicapps.models.Album
import com.autodhil.musicapps.repository.Repository
import com.autodhil.musicapps.views.detailalbum.DetailAlbumActivity
import com.autodhil.musicapps.views.playsong.PlaySongActivity
import hide
import org.jetbrains.anko.startActivity
import visible


class TopAlbumsFragment : Fragment() {

    private var _binding: FragmentTopAlbumsBinding? = null
    private val topAlbumsBinding get() = _binding
    private lateinit var topAlbumsAdapter: TopAlbumsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTopAlbumsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topAlbumsAdapter = TopAlbumsAdapter()
        swipeMyTracks()
        onCLick()


        showLoading()
        Handler(Looper.getMainLooper()).postDelayed({
            showTopAlbums()
        },2000)
    }

    private fun onCLick() {
        topAlbumsAdapter.onClick{album ->
            context?.startActivity<DetailAlbumActivity>(DetailAlbumActivity.KEY_ALBUM to album)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(){
        topAlbumsBinding?.swipeTopAlbums?.visible()
    }

    private fun hideLoading(){
        topAlbumsBinding?.swipeTopAlbums?.hide()
    }

    private fun swipeMyTracks(){
        topAlbumsBinding?.swipeTopAlbums?.setOnRefreshListener {
            showTopAlbums()
        }
    }


    private fun showTopAlbums() {
        hideLoading()
        val topCharts = Repository.getDataTopAlbumsFromAssets(context)
        topAlbumsAdapter.setData(topCharts as MutableList<Album>)
        //SetupRecyclerView

        topAlbumsBinding?.rvTopAlbums?.adapter = topAlbumsAdapter

    }

}