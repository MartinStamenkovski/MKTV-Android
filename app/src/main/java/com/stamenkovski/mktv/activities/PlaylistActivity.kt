package com.stamenkovski.mktv.activities

import android.app.UiModeManager
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.martin.retrofitwrapper.retrofit.onEnqueue
import com.stamenkovski.mktv.extensions.makeErrorSnackbarWith
import com.stamenkovski.mktv.models.PlayItem
import com.stamenkovski.mktv.adapters.PlaylistAdapter
import com.stamenkovski.mktv.R
import com.stamenkovski.mktv.utils.M
import com.stamenkovski.mktv.utils.ItemClickSupport
import com.stamenkovski.mktv.utils.Logger
import com.stamenkovski.mktv.utils.retrofit.RT
import kotlinx.android.synthetic.main.activity_playlist.*

class PlaylistActivity : AppCompatActivity() {

    private lateinit var playlistRecyclerView: RecyclerView
    private var playlist = mutableListOf<PlayItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)

        this.playlistRecyclerView = recyclerView_playList
        this.loadTVChannels()
    }

    private fun loadTVChannels() {
        RT.request.tv.onEnqueue(
            onSuccess = { response ->
                response.body()?.let {
                    this.setupRecyclerViewAdapterWith(it)
                    this.onItemClick()
                }
            },
            onFailure = {
                this.makeErrorSnackbarWith(it.message, playlistRecyclerView) {
                    this.loadTVChannels()
                }
            }
        )
    }

    private fun setupRecyclerViewAdapterWith(playlist: MutableList<PlayItem>) {
        this.playlist = playlist
        val uiModeManager = getSystemService(UI_MODE_SERVICE) as UiModeManager
        if (uiModeManager.currentModeType == Configuration.UI_MODE_TYPE_TELEVISION) {
            Logger.d("Running on a TV Device")
            this.playlistRecyclerView.layoutManager = GridLayoutManager(this, 3)
        } else {
            Logger.d("Running on a non-TV Device")
            this.playlistRecyclerView.layoutManager = LinearLayoutManager(this)
        }

        this.playlistRecyclerView.adapter = PlaylistAdapter(this, playlist)
    }

    private fun onItemClick() {
        ItemClickSupport.addTo(playlistRecyclerView).setOnItemClickListener { _, position, _ ->
            this.showVideoFor(playlist[position].stream)
        }
    }

    private fun showVideoFor(stream: String) {
        val intent = Intent(this, VideoActivity::class.java);
        intent.putExtra(M.STREAM.URL, stream)
        this.startActivity(intent)
    }
}
