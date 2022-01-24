package com.autodhil.musicapps.views.mytracks

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.autodhil.musicapps.adapter.MyTracksAdapter
import com.autodhil.musicapps.databinding.FragmentMyTracksBinding
import com.autodhil.musicapps.models.Song
import com.autodhil.musicapps.repository.Repository
import com.autodhil.musicapps.views.playsong.PlaySongActivity
import hide
import org.jetbrains.anko.startActivity
import visible


class MyTracksFragment : Fragment() {
    private var _binding: FragmentMyTracksBinding? = null
    private val myTracksBinding get() = _binding
    private lateinit var myTracksAdapter: MyTracksAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyTracksBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myTracksAdapter = MyTracksAdapter()
        swipeMyTracks()
        onCLick()


        showLoading()
        Handler(Looper.getMainLooper()).postDelayed({
            showMyTracks()
        },2000)
    }

    private fun onCLick() {
        myTracksAdapter.onClick { songs, position ->
            context?.startActivity<PlaySongActivity>(
                PlaySongActivity.KEY_SONG to songs,
                PlaySongActivity.KEY_POSITION to position
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(){
        myTracksBinding?.swipeMyTracks?.visible()
    }

    private fun hideLoading(){
        myTracksBinding?.swipeMyTracks?.hide()
    }

    private fun swipeMyTracks(){
        myTracksBinding?.swipeMyTracks?.setOnRefreshListener {
            showMyTracks()
        }
    }


    private fun showMyTracks() {
        hideLoading()
        val topCharts = Repository.getDataTopChartsFromAssets(context)
        myTracksAdapter.setData(topCharts as MutableList<Song>)
        //SetupRecyclerView

        myTracksBinding?.rvMyTracks?.adapter = myTracksAdapter

    }


}