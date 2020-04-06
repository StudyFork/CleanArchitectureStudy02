package com.mhuman.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_second_home_screen.view.*
import kotlinx.android.synthetic.main.fragment_third_home_screen.view.*

class SecondHomeScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.btn_second_home.setOnClickListener {
            val direction: NavDirections = FirstHomeScreenDirections.actionFirstHomeScreenToTwoDepthScreen(2)
            findNavController().navigate(direction)
        }
    }
}
