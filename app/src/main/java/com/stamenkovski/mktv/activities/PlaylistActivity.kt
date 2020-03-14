package com.stamenkovski.mktv.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.martin.retrofitwrapper.retrofit.onEnqueue
import com.stamenkovski.mktv.extensions.makeErrorSnackbarWith
import com.stamenkovski.mktv.models.PlayItem
import com.stamenkovski.mktv.PlaylistAdapter
import com.stamenkovski.mktv.R
import com.stamenkovski.mktv.utils.C
import com.stamenkovski.mktv.utils.ItemClickSupport
import com.stamenkovski.mktv.utils.retrofit.RT
import kotlinx.android.synthetic.main.activity_playlist.*

class PlaylistActivity : AppCompatActivity() {

    lateinit var playlistRecyclerView: RecyclerView
    private var playlist = mutableListOf<PlayItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)

        this.playlistRecyclerView = recyclerView_playList
        this.playlistRecyclerView.layoutManager = LinearLayoutManager(this)
        this.loadTVChannels()
    }

    private fun loadTVChannels() {
        RT.request.tv.onEnqueue(
            onSuccess = { response ->
                response.body()?.let {
                    this.playlist = it
                    this.playlistRecyclerView.adapter = PlaylistAdapter(this, it)
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

    private fun onItemClick() {
        ItemClickSupport.addTo(playlistRecyclerView).setOnItemClickListener { _, position, _ ->
            this.showVideoFor(playlist[position].stream)
        }
    }

    private fun showVideoFor(stream: String) {
        val intent = Intent(this, VideoActivity::class.java);
        intent.putExtra(C.STREAM.URL, stream)
        this.startActivity(intent)
    }
}
