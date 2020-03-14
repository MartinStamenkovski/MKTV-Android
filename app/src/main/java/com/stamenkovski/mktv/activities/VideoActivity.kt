package com.stamenkovski.mktv.activities

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.stamenkovski.mktv.extensions.makeErrorSnackbarWith
import com.stamenkovski.mktv.R
import com.stamenkovski.mktv.extensions.hidden
import com.stamenkovski.mktv.extensions.visible
import com.stamenkovski.mktv.utils.C
import com.stamenkovski.mktv.utils.Logger
import kotlinx.android.synthetic.main.activity_video.*


class VideoActivity : AppCompatActivity(), Player.EventListener {

    private lateinit var exoPlayerView: PlayerView
    private var player: SimpleExoPlayer? = null
    private lateinit var progressLoading: ProgressBar

    private lateinit var streamURL: String
    private var shouldAutoPlay = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        this.initUI()

        intent.extras?.let {
            it.getString(C.STREAM.URL)?.run {
                this@VideoActivity.streamURL = this
            }
        }
        savedInstanceState?.let {
            this.shouldAutoPlay = it.getBoolean(C.STATE.AUTO_PLAY, true)
        }
    }

    private fun initUI() {
        this.progressLoading = progressBar_loading
        this.exoPlayerView = exoplayer_view
    }

    private fun setupExoPlayerWith(stream: String) {

        val dataSourceFactory = DefaultHttpDataSourceFactory(Util.getUserAgent(this, "mktv"))
        val hlsMediaSource: HlsMediaSource = HlsMediaSource.Factory(dataSourceFactory)
                .setAllowChunklessPreparation(true)
                .createMediaSource(Uri.parse(stream))

        player = SimpleExoPlayer.Builder(this).build()

        this.player?.prepare(hlsMediaSource)
        this.player?.playWhenReady = shouldAutoPlay
        this.player?.addListener(this)
        this.exoPlayerView.player = player

    }

    override fun onPlayerError(error: ExoPlaybackException) {
        this.makeErrorSnackbarWith("Video could not be loaded", exoPlayerView) {
            this.player?.retry()
        }
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        when(playbackState) {
            Player.STATE_IDLE -> {
            }
            Player.STATE_READY -> {
                this.progressLoading.hidden()
            }
            Player.STATE_BUFFERING -> {
                this.progressLoading.visible()
            }
            Player.STATE_ENDED -> {
            }
        }
    }

    private fun hideSystemUi() {
        this.exoPlayerView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    override fun onResume() {
        super.onResume()
        this.hideSystemUi()
        this.setupExoPlayerWith(streamURL)
    }
    override fun onPause() {
        super.onPause()
        this.shouldAutoPlay = false
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        this.player?.stop()
        this.player?.release()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(C.STATE.AUTO_PLAY, false)
    }

}
