package com.god.taeiim.movieapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.god.taeiim.movieapp.BR
import com.god.taeiim.movieapp.R
import com.god.taeiim.movieapp.base.BaseActivity
import com.god.taeiim.movieapp.data.model.Movie
import com.god.taeiim.movieapp.databinding.ActivityMainBinding
import com.god.taeiim.movieapp.databinding.ItemMoviePopularBinding
import com.god.taeiim.movieapp.ext.observe
import com.god.taeiim.movieapp.ui.MovieRecyclerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var movieAdapter: MovieRecyclerAdapter<Movie, ItemMoviePopularBinding>

    private val stateObserver = Observer<MainViewModel.ViewState> {
        movieAdapter.updateItems(it.movies)
        binding.pbLoading.visibility = if (it.isLoading) View.VISIBLE else View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieAdapter = MovieRecyclerAdapter(R.layout.item_movie_popular, BR.movie)

        binding.rvMovie.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        observe(viewModel.stateLiveData, stateObserver)
        viewModel.loadData()
    }

}
