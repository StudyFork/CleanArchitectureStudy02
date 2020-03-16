package com.egiwon.moviesearch.ui

import android.os.Bundle
import com.egiwon.moviesearch.BR
import com.egiwon.moviesearch.R
import com.egiwon.moviesearch.base.BaseActivity
import com.egiwon.moviesearch.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind {
            vm = viewModel
            rvMovieList.adapter = MainMovieAdapter(bindingId = BR.movie, viewModel = viewModel)
            rvMovieList.setHasFixedSize(true)
        }

        viewModel.loadPopularMovies()
    }
}
