package com.example.movieapplication.presenter.moviedetail

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.BR
import com.example.movieapplication.R
import com.example.movieapplication.base.BaseFragment
import com.example.movieapplication.base.liverecyclerview.bindData
import com.example.movieapplication.base.simplerecyclerview.SimpleRecyclerViewAdapter
import com.example.movieapplication.base.simplerecyclerview.SimpleViewHolder
import com.example.movieapplication.databinding.FragmentMovieDetailBinding
import com.example.movieapplication.databinding.ItemActorBinding
import com.example.movieapplication.presenter.model.MovieDetail
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(R.layout.fragment_movie_detail) {

    private val movieDetailViewModel: MovieDetailViewModel by viewModel {

        //SafeArgs 받는 방법 1
        val safeArgs: MovieDetailFragmentArgs by navArgs()
        val movieId = safeArgs.movieId

        //SafeArgs 받는 방법2
        /*arguments?.let {
            val movieId = MovieDetailFragmentArgs.fromBundle(it).movieId
        }*/

        parametersOf(movieId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieDetailViewModel = movieDetailViewModel

        initObservable()
        initLiveRecyclerView()
        //initSimpleRecyclerView()
        movieDetailViewModel.loadMoveDetail()

    }

    private fun initObservable() {
        movieDetailViewModel.toastLiveData.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { msg ->
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initLiveRecyclerView() {
        movieDetailViewModel.actors.observe(viewLifecycleOwner, Observer {
            binding.rvMovieDetailActors.bindData(it, R.layout.item_actor, viewLifecycleOwner)
        })
    }

    private fun initSimpleRecyclerView() {
        val simpleAdapter = object : SimpleRecyclerViewAdapter<MovieDetail.Actor, ItemActorBinding>(
            R.layout.item_actor,
            BR.model
        ) {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): SimpleViewHolder<ItemActorBinding> {
                return super.onCreateViewHolder(parent, viewType).apply {

                    val position = this.adapterPosition
                    if (position == RecyclerView.NO_POSITION) return@apply

                    val item = getItem(position)

                    itemView.setOnClickListener {
                        Toast.makeText(it.context, item.name, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        with(binding.rvMovieDetailActors) {
            adapter = simpleAdapter
        }

        movieDetailViewModel.actors.observe(viewLifecycleOwner, Observer {
            simpleAdapter.replaceAll(it)
        })
    }
}