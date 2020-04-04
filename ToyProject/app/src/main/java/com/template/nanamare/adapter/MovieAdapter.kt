package com.template.nanamare.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.template.nanamare.R
import com.template.nanamare.base.ui.BaseViewHolder
import com.template.nanamare.data.vo.Movie
import com.template.nanamare.databinding.ItemMovieBinding
import com.template.nanamare.network.response.MovieResponse

class MovieAdapter : PagedListAdapter<MovieResponse.Result, RecyclerView.ViewHolder>(
    COMPARATOR
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return object : BaseViewHolder<MovieResponse.Result, ItemMovieBinding>(
            R.layout.item_movie, parent
        ) {
            init {
                itemView.setOnClickListener {
                }
            }

            override fun onViewCreated(item: MovieResponse.Result?) {
                binding.run {
                    item?.let {
                        vm = Movie(it.title, it.releaseDate, it.posterPath)
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? BaseViewHolder<*, *>)?.onBindViewHolder(getItem(position))
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<MovieResponse.Result>() {
            private val PAYLOAD_SCORE = Any()
            override fun areContentsTheSame(
                oldItem: MovieResponse.Result,
                newItem: MovieResponse.Result
            ): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(
                oldItem: MovieResponse.Result,
                newItem: MovieResponse.Result
            ): Boolean =
                oldItem === newItem

            override fun getChangePayload(
                oldItem: MovieResponse.Result,
                newItem: MovieResponse.Result
            ): Any? {
                return if (sameExceptScore(oldItem, newItem)) {
                    PAYLOAD_SCORE
                } else {
                    null
                }
            }

            private fun sameExceptScore(
                oldItem: MovieResponse.Result,
                newItem: MovieResponse.Result
            ): Boolean {
                // DON'T do this copy in a real app, it is just convenient here for the demo :)
                // because reddit randomizes scores, we want to pass it as a payload to minimize
                // UI updates between refreshes
                return oldItem.copy() == newItem
            }
        }
    }

}