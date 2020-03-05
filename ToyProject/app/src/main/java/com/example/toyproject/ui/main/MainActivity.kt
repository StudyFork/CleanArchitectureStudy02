package com.example.toyproject.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.toyproject.R
import com.example.toyproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: MainMoviePostingRecyclerAdapter
    val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        initailize()
        initBinding()
        test()
    }

    private fun initailize() {
        adapter = MainMoviePostingRecyclerAdapter()
        binding.rcvPoster.layoutManager = GridLayoutManager(this@MainActivity, 3)
    }

    private fun initBinding() {
        binding.vm = mainViewModel
        binding.lifecycleOwner = this@MainActivity
        binding.rcvPoster.adapter = adapter
    }

    private fun test() {
        mainViewModel.searchMovie()
    }
}
