package com.zee.streamingwithexoplayer.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zee.streamingwithexoplayer.databinding.VideoTrackBtmSheetBinding
import com.zee.streamingwithexoplayer.databinding.VideoTrackSingleItemBinding


class VideoTrackBtmSheet : BottomSheetDialogFragment() {

    private var binding: VideoTrackBtmSheetBinding? = null
    private val tracks = listOf("144p", "240p", "360p", "480p", "720p", "1080p")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VideoTrackBtmSheetBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            tracks.forEach { track ->
                val trackBinding = VideoTrackSingleItemBinding.inflate(
                    LayoutInflater.from(requireContext()),
                    null,
                    false
                )

                trackBinding.root.text = track
                root.addView(trackBinding.root)
            }

        }
    }



}