package com.god.taeiim.movieapp.ui

import android.os.Bundle
import com.god.taeiim.movieapp.R
import com.god.taeiim.movieapp.base.BaseActivity
import com.god.taeiim.movieapp.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}
