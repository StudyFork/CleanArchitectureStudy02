package com.tistory.blackjin.navigationapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tistory.blackjin.navigationapp.R
import kotlinx.android.synthetic.main.screen_third_home.*

class ThirdHomeScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("MyTag","ThirdHomeScreen onCreateView")
        return inflater.inflate(R.layout.screen_third_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_third_home.setOnClickListener {
            findNavController().navigate(R.id.action_global_twoDepthScreen, bundleOf("param_count" to 100))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("MyTag","ThirdHomeScreen onDestroyView")
    }
}