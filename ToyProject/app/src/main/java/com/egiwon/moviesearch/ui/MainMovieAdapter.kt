package com.egiwon.moviesearch.ui

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.moviesearch.R
import com.egiwon.moviesearch.base.BaseRecyclerView
import com.egiwon.moviesearch.data.model.MovieEntity
import com.egiwon.moviesearch.databinding.ItemMovieBinding

class MainMovieAdapter(
    @LayoutRes private val layoutResId: Int = R.layout.item_movie,
    private val bindingId: Int,
    private val viewModel: MainViewModel
) : BaseRecyclerView.BaseAdapter<MovieEntity, ItemMovieBinding>(
    layoutResId,
    bindingId
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerView.BaseViewHolder<ItemMovieBinding> =
        MovieViewHolder(parent, layoutResId, bindingId)

    inner class MovieViewHolder(
        parent: ViewGroup,
        resourceId: Int,
        bindingId: Int
    ) : BaseRecyclerView.BaseViewHolder<ItemMovieBinding>(parent, resourceId, bindingId) {

        init {
            binding.vm = viewModel
        }
    }

}