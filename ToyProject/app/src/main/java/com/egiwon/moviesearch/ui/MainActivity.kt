package com.egiwon.moviesearch.ui

import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import com.egiwon.moviesearch.BR
import com.egiwon.moviesearch.R
import com.egiwon.moviesearch.base.BaseActivity
import com.egiwon.moviesearch.base.BasePagedAdapter
import com.egiwon.moviesearch.base.BaseViewModel
import com.egiwon.moviesearch.data.model.MovieEntity
import com.egiwon.moviesearch.databinding.ActivityMainBinding
import com.egiwon.moviesearch.databinding.ItemMovieBinding
import com.egiwon.moviesearch.ui.detail.MovieDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind {
            vm = viewModel
            initAdapter()
        }

        viewModel.loadPopularMovies()
        observingViewModel()
    }

    override fun ActivityMainBinding.initAdapter() {
        rvMovieList.adapter = object : BasePagedAdapter<MovieEntity, ItemMovieBinding>(
            R.layout.item_movie,
            BR.movie,
            mutableMapOf<Int?, BaseViewModel>().apply {
                put(BR.vm, viewModel)
            }
        ) {}
        rvMovieList.setHasFixedSize(true)
    }

    private fun observingViewModel() {
        viewModel.movie.observe(this, Observer {
            val intent = Intent(this, MovieDetailActivity::class.java).apply {
                putExtra(KEY_MOVIE_ID, it.id)
            }

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
            startActivity(intent, options.toBundle())
        })

    }

    companion object {
        const val KEY_MOVIE_ID = "key_movie_id"
    }
}
