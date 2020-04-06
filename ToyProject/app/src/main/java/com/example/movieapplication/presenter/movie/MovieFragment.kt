package com.example.movieapplication.presenter.movie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.R
import com.example.movieapplication.base.BaseFragment
import com.example.movieapplication.databinding.FragmentMovieBinding
import com.example.movieapplication.presenter.adapter.MovieAdapter
import com.example.movieapplication.presenter.adapter.itemdecoration.MovieItemDecoration
import com.example.movieapplication.presenter.model.MovieItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {

    private val movieViewModel: MovieViewModel by viewModel()

    private val movieAdapter by lazy {
        MovieAdapter().apply {
            onItemClickListener = object : MovieAdapter.OnItemClickListener {
                override fun onItemClick(data: MovieItem) {

                    //SafeArgs 보내는 방법 1
                    val direction =
                        MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(data.id)
                    findNavController().navigate(direction)

                    //SafeArgs 보내는 방법 2
                    //findNavController().navigate(R.id.action_movieFragment_to_movieDetailFragment, bundleOf("movieId" to data.id))
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieViewModel = movieViewModel

        initObservable()
        initRecyclerView()
        movieViewModel.loadMovie()
    }


    private fun initObservable() {
        movieViewModel.movies.observe(viewLifecycleOwner, Observer {
            movieAdapter.addItems(it)
        })

        movieViewModel.bottomLoading.observe(viewLifecycleOwner, Observer { flag ->
            if (flag) {
                movieAdapter.addBottomLoading()
                binding.rvMovie.scrollToPosition(movieAdapter.itemCount - 1)
            } else {
                movieAdapter.removeBottomLoading()
            }
        })

        movieViewModel.toastLiveData.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { msg ->
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initRecyclerView() {
        with(binding.rvMovie) {

            layoutManager = GridLayoutManager(this.context, MovieAdapter.spanCount).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return MovieAdapter.ViewType.getViewType(
                            movieAdapter.getItemViewType(
                                position
                            )
                        ).spanCount
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