package com.tistory.blackjin.navigationapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tistory.blackjin.navigationapp.NavMainDirections
import com.tistory.blackjin.navigationapp.R
import kotlinx.android.synthetic.main.screen_second_home.*

class SecondHomeScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.screen_second_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_second_home.setOnClickListener {
            val navDirection = NavMainDirections.actionGlobalTwoDepthScreen(20)
            findNavController().navigate(navDirection)
        }
    }
}