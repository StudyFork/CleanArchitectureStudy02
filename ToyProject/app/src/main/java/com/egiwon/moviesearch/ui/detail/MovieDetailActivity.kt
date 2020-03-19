package com.egiwon.moviesearch.ui.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.egiwon.moviesearch.R
import com.egiwon.moviesearch.base.BaseActivity
import com.egiwon.moviesearch.databinding.ActivityMovieDetailBinding
import com.egiwon.moviesearch.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding, MovieDetailViewModel>(
    R.layout.activity_movie_detail
) {

    override val viewModel: MovieDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieId = intent.getIntExtra(MainActivity.KEY_MOVIE_ID, 0)
        viewModel.getMovieDetailInfo(movieId)
        observingViewModel()
    }

    private fun observingViewModel() {
        viewModel.movieDetailInfo.observe(this, Observer {
            binding.movieDetailInfo = it
        })
    }
}