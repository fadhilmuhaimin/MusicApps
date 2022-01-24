package com.autodhil.musicapps.views.topcharts

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.autodhil.musicapps.R
import com.autodhil.musicapps.adapter.TopChartsAdapter
import com.autodhil.musicapps.databinding.FragmentTopChartsBinding
import com.autodhil.musicapps.models.Song
import com.autodhil.musicapps.repository.Repository
import com.autodhil.musicapps.views.playsong.PlaySongActivity
import gone
import hide
import org.jetbrains.anko.startActivity
import visible


class TopChartsFragment : Fragment() {

    private var _binding: FragmentTopChartsBinding? = null
    private val topChartsbinding get() = _binding
    private lateinit var topChartsAdapter : TopChartsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTopChartsBinding.inflate(inflater,container,false)

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topChartsAdapter = TopChartsAdapter()
        swipeTopChats()
        onCLick()


        showLoading()
        Handler(Looper.getMainLooper()).postDelayed({
            showTopCharts()
        },2000)
    }

    private fun onCLick() {
        topChartsAdapter.onClick { songs, position ->
            context?.startActivity<PlaySongActivity>(
                PlaySongActivity.KEY_SONG to songs,
                PlaySongActivity.KEY_POSITION to position
            )
        }
    }

    private fun showTopCharts() {
        hideLoading()
        val topCharts = Repository.getDataTopChartsFromAssets(context)
        topChartsAdapter.setData(topCharts as MutableList<Song>)
        //SetupRecyclerView
        topChartsbinding?.rvTopCharts?.adapter = topChartsAdapter

    }


    private fun swipeTopChats(){
        topChartsbinding?.swipeTopCharts?.setOnRefreshListener {
            showTopCharts()
        }
    }

    private fun showLoading(){
        topChartsbinding?.swipeTopCharts?.visible()
    }

    private fun hideLoading(){
        topChartsbinding?.swipeTopCharts?.hide()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}