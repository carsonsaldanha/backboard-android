package com.jrtc.backboard.ui.highlights

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.jrtc.backboard.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityVideoBinding.inflate(layoutInflater)
    }
    private var player: ExoPlayer? = null
    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        if (player == null) {
            initializePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(this)
            .build()
            .also { exoPlayer ->
                binding.highlightPlayerView.player = exoPlayer
                val mediaItem = MediaItem.fromUri("https://cdn-cf-east.streamable.com/video/mp4/doda1w.mp4?Expires=1668116940&Signature=k35p-Kuzm-ZLX3NX-jpCnwSySdtF-pPRSr5EJ4SK2z3oJ6CqbyRKqS02l93hdXY9KaeERSRwFGW9AbHjSyHjeL8gjmCnHlwDhbktpx9~zA8wHdXFR6k6AZuq4QkT3c7EHCD4slMzV7-bGPwPAMFg7JJzG4Aop09J~tyyoy4oC5dgFgyx9MYKKxQQ5maf86KwggShqLEOe9eX14~6J4wlzSJMtBwvjC-NN358eSBZs5kFdBXw54LWBQSoJ~Kn6LrxH4sObzrbcDbcoRra5UZYTeAVOlDXcSmQpC8AFKy6N3nf4rkwWISWRcdcI1BCq3Bjtcot-Ww27KYgrzWJ5i-~4w__&Key-Pair-Id=APKAIEYUVEN4EVB2OKEQ")
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentItem, playbackPosition)
                exoPlayer.prepare()
            }
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.highlightPlayerView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun releasePlayer() {
        player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentItem = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
        }
        player = null
    }

}
