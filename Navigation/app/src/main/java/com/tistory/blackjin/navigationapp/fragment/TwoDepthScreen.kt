package com.tistory.blackjin.navigationapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.tistory.blackjin.navigationapp.R
import kotlinx.android.synthetic.main.screen_two_depth.*

class TwoDepthScreen : Fragment() {

    private val twoDepArgs: TwoDepthScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.screen_two_depth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txt_two_depth.text = twoDepArgs.paramCount.toString()
    }
}