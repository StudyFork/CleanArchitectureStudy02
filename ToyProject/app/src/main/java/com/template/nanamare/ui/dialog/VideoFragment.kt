package com.template.nanamare.ui.dialog

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.SparseArray
import android.view.View
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.template.nanamare.AppApplication
import com.template.nanamare.R
import com.template.nanamare.base.ui.BaseDialogFragment
import com.template.nanamare.databinding.VideoDialogBinding
import com.template.nanamare.vm.VideoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

@SuppressLint("StaticFieldLeak")
class VideoFragment(private val liveVideoUrl: String? = null) :
    BaseDialogFragment<VideoDialogBinding>(R.layout.video_dialog) {

    private val videoViewModel by viewModel<VideoViewModel> {
        parametersOf(liveVideoUrl)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializePlayer()
    }

    private fun initializePlayer() {
        val dataSourceFactory = DefaultDataSourceFactory(
            context,
            Util.getUserAgent(requireContext(), AppApplication.TAG_APPLICATION)
        )
        object : YouTubeExtractor(requireContext()) {
            override fun onExtractionComplete(ytFiles: SparseArray<YtFile>, vMeta: VideoMeta) {
                val iTag = 22 // quality
                val videoSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(ytFiles[iTag].url))
                binding.videoView.player = SimpleExoPlayer.Builder(requireContext()).build().apply {
                    playWhenReady = playWhenReady;
                    seekTo(currentWindowIndex, 0)
                    prepare(videoSource, false, false)
                }

            }
        }.extract(videoViewModel.liveVideoPath.value, true, true)

    }

    override fun onStop() {
        super.onStop()
        binding.videoView.player?.release()
    }


}