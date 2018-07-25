package com.kevin.newsapp.ui.main.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.kevin.newsapp.BuildConfig
import com.kevin.newsapp.R

class PlayerFragment : YouTubePlayerSupportFragment() {

    // TODO RELEASE WHEN?
    private lateinit var mPlayer: YouTubePlayer

    private var videoId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        videoId = arguments?.getString(VIDEO_ID)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        initialize()

        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    private fun initialize() {
        initialize(BuildConfig.YOUTUBE_API_KEY, object: YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean) {
                if(wasRestored) {
                    mPlayer = player
                    mPlayer.loadVideo(videoId)
                }
            }

            override fun onInitializationFailure(p0: YouTubePlayer.Provider, p1: YouTubeInitializationResult) {
                // TODO
            }
        })
    }

    companion object {
        val TAG = PlayerFragment::class.java.simpleName

        private const val VIDEO_ID = "video_id"

        fun newInstance(bundle: Bundle): PlayerFragment {
            return PlayerFragment().apply { arguments = bundle }
        }
    }
}
