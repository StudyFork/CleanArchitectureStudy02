package com.example.movieapplication.presenter.movie

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.R
import com.example.movieapplication.base.BaseFragment
import com.example.movieapplication.databinding.FragmentMovieBinding
import com.example.movieapplication.presenter.adapter.MovieAdapter
import com.example.movieapplication.presenter.adapter.itemdecoration.MovieItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {

    private val movieViewModel: MovieViewModel by viewModel()

    private val movieAdapter by lazy { MovieAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.moviewViewModel = movieViewModel

        initObservable()
        initRecyclerView()
        movieViewModel.loadMovie()
    }


    private fun initObservable() {

        movieViewModel.movies.observe(this, Observer {
            movieAdapter.addItems(it)
        })

        movieViewModel.loading.observe(this, Observer { flag ->
            if (flag) {
                binding.pbMovie.visibility = View.VISIBLE
            } else {
                binding.pbMovie.visibility = View.GONE
            }
        })

        movieViewModel.bottomLoading.observe(this, Observer { flag ->
            if (flag) {
                movieAdapter.addBottomLoading()
                binding.rvMovie.scrollToPosition(movieAdapter.itemCount - 1)
            } else {
                movieAdapter.removeBottomLoading()
            }
        })
    }

    private fun initRecyclerView() {
        with(binding.rvMovie) {

            layoutManager = GridLayoutManager(this.context, MovieAdapter.spanCount).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (MovieAdapter.ViewType.getViewType(
                            movieAdapter.getItemViewType(position)
                        )) {
                            MovieAdapter.ViewType.MOVIE -> 1
                            MovieAdapter.ViewType.LOADING -> 2
                        }
                    }
                }
            }

            adapter = movieAdapter

            itemAnimator = null
            addItemDecoration(MovieItemDecoration())

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (dy > 0) {
                        val lm = recyclerView.layoutManager
                        if (lm is GridLayoutManager) {
                            if (lm.findLastCompletelyVisibleItemPosition() == recyclerView.adapter?.itemCount?.minus(
                                    1
                                )
                            ) {
                                movieViewModel.addMovie()
                            }
                        }
                    }

                }
            })
        }
    }
}