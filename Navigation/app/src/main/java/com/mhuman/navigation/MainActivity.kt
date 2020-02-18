package com.mhuman.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*인자로 view와 navController를 인자로 받아 view를 navController에 맞게 구현
        이 과정에서 bottomNavigation의 menu값의 id와 fragment 목적지가 자동으로 맵핑 됨*/
        NavigationUI.setupWithNavController(main_bottom_navigation, findNavController(R.id.nav_host))
    }
}
