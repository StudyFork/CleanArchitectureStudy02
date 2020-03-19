package com.egiwon.moviesearch.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.egiwon.moviesearch.BR
import com.egiwon.moviesearch.R
import com.egiwon.moviesearch.base.BaseActivity
import com.egiwon.moviesearch.base.onItemClick
import com.egiwon.moviesearch.databinding.ActivityMainBinding
import com.egiwon.moviesearch.ui.detail.MovieDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModel()

    private val onMovieItemClick: onItemClick<Int> = {
        val intent = Intent(this, MovieDetailActivity::class.java).apply {
            putExtra(KEY_MOVIE_ID, it)
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind {
            vm = viewModel
            rvMovieList.adapter = MainMovieAdapter(
                bindingId = BR.movie,
                viewModel = viewModel,
                onItemClick = onMovieItemClick
            )
            rvMovieList.setHasFixedSize(true)
        }

        viewModel.loadPopularMovies()
        observingViewModel()
    }

    private fun observingViewModel() {
        viewModel.resultMovieList.observe(this, Observer {
            (binding.rvMovieList.adapter as? MainMovieAdapter)?.run {
                replaceAll(it)
            }
        })
    }

    companion object {
        const val KEY_MOVIE_ID = "key_movie_id"
    }
}
