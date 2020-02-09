package com.tistory.blackjin.navigationapp.fragment

import android.os.Bundle
import android.util.Log
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
        Log.d("MyTag", "TwoDepthScreen onCreateView")
        return inflater.inflate(R.layout.screen_two_depth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTextView()
        initButton()
    }

    private fun initTextView() {

        /*arguments?.let {
            txt_two_depth.text = TwoDepthScreenArgs.fromBundle(it).paramCount.toString()
        }*/

        txt_two_depth.text = twoDepArgs.paramCount.toString()
    }

    private fun initButton() {

        button.setOnClickListener {
            //앞으로가기
            //val navDirection = NavMainDirections.actionGlobalThirdHomeScreen()
            //findNavController().navigate(navDirection)

            //앞으로가기
            //findNavController().navigate(R.id.action_global_thirdHomeScreen)

            //뒤로가기
            //findNavController().navigateUp()

        }

        /*button.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_global_thirdHomeScreen)
        )*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("MyTag", "TwoDepthScreen onDestroyView")
    }
}