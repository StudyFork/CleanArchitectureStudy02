package com.nanamare.mac.navigation

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.nanamare.mac.navigation.fragment.FirstFragment
import com.nanamare.mac.navigation.fragment.SecondFragment
import com.nanamare.mac.navigation.fragment.ThirdFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FirstFragment.OnFragmentInteractionListener,
    SecondFragment.OnFragmentInteractionListener, ThirdFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // auto binding bottom navigation & fragment
        NavigationUI.setupWithNavController(
            navigation,
            findNavController(R.id.nav_host)
        )
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
