package com.example.tiktokclone

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_FILL
import com.google.android.exoplayer2.ui.PlayerView

class VideoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.video_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val playerView = view.findViewById<PlayerView>(R.id.player_view)
        playerView.hideController()
        val player = ExoPlayer.Builder(view.context).build();
        player.repeatMode = Player.REPEAT_MODE_ALL;
        playerView.player = player
        playerView.resizeMode = RESIZE_MODE_FILL
        // Add the media item in
        val videoId = when (arguments?.getInt("videoId")) {
            0 -> R.raw.video_1
            1 -> R.raw.video_2
            2 -> R.raw.video_3
            else -> null
        }
        val videoUri = Uri.parse("android.resource://com.example.tiktokclone/" + videoId)
        val mediaItem = MediaItem.fromUri(videoUri)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()

    }

    companion object {

        fun newInstance(videoId: Int): VideoFragment {
            val args = Bundle()
            args.putInt("videoId", videoId)
            val fragment = VideoFragment()
            fragment.arguments = args
            return fragment
        }

    }
}