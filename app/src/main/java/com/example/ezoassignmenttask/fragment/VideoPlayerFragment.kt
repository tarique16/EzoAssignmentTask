package com.example.ezoassignmenttask.fragment

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import androidx.core.animation.doOnStart
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.ezoassignmenttask.databinding.FragmentVideoPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.video.VideoSize

class VideoPlayerFragment : Fragment() {

    private var _binding: FragmentVideoPlayerBinding? = null
    private val binding get() = _binding!!
    private lateinit var player: ExoPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoPlayerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // Initialize ExoPlayer
            player = ExoPlayer.Builder(requireActivity()).build()
            playerView.player = player

            // Set media source
            val mediaItem =
                MediaItem.fromUri(Uri.parse("https://d2s7v2mzcfdtyp.cloudfront.net/event/f457c545a9ded88f18ecee47145a72c0/card/Pzs44FQZpLEIwVtjpey2Aycte7rHjf1A2wWQUpj7.mp4"))
            player.setMediaItem(mediaItem)
            player.prepare()

            // Set repeat mode to loop the video
            player.repeatMode = Player.REPEAT_MODE_ALL

            // Show loader while loading
            player.addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(state: Int) {
                    progressBar.isVisible = state == Player.STATE_BUFFERING
                    if (state == Player.STATE_READY) {
                        // Auto-play the video when ready
                        player.play()
                    }
                }

                override fun onVideoSizeChanged(videoSize: VideoSize) {
                    // Adjust the PlayerView height to match the video height
                    val aspectRatio = videoSize.width / videoSize.height.toFloat()
                    increaseViewSize(binding.playerView,(binding.playerView.width / aspectRatio).toInt())
                    /*layoutParams.height = (binding.playerView.width / aspectRatio).toInt()
                    binding.playerView.layoutParams = layoutParams*/
                }
            })
            ivPlayPause.visibility = GONE
            // Play/pause toggle on PlayerView click
            playerView.setOnClickListener {
                if (player.isPlaying) {
                    player.pause()
                } else {
                    player.play()
                }
                ivPlayPause.visibility = VISIBLE
                fadeOutAndHideImage(ivPlayPause)
            }
        }
    }
    fun increaseViewSize(view: View, increaseValue: Int) {
        /*val valueAnimator = ValueAnimator.ofInt(increaseValue)
        valueAnimator.duration = 750L
        valueAnimator.addUpdateListener {
            val animatedValue = valueAnimator.animatedValue as Int
            val layoutParams = view.layoutParams
            layoutParams.height = animatedValue
            view.layoutParams = layoutParams
        }
        valueAnimator.start()*/
        val valueAnimator = ValueAnimator.ofInt(increaseValue).apply {
            addUpdateListener {
                val params = view.layoutParams
                params.height = animatedValue as Int
                view.layoutParams = params
            }
            doOnStart {
//                viewModel.newHeight = videoHeight
            }
            duration = 1000L
            interpolator = AccelerateDecelerateInterpolator()
        }
        val objectAnimator = ObjectAnimator.ofFloat(
            view,
            "scaleY",
            0f,
            1f
        ).apply {
            duration = 1000L
            interpolator = AccelerateDecelerateInterpolator()
        }
        AnimatorSet().apply {
            playTogether(valueAnimator, objectAnimator)
            start()
        }
    }
    private fun fadeOutAndHideImage(img: ImageView) {
        val fadeOut = AlphaAnimation(1F, 0F)
        fadeOut.interpolator = AccelerateInterpolator()
        fadeOut.duration = 1500

        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation) {
                img.visibility = GONE
            }

            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationStart(animation: Animation) {}
        })
        img.startAnimation(fadeOut)
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}