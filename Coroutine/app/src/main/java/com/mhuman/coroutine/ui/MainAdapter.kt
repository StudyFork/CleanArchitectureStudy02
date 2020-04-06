package com.mhuman.coroutine.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mhuman.coroutine.R
import com.mhuman.coroutine.databinding.ItemMovieListBinding
import com.mhuman.coroutine.ext.loadUrl
import com.mhuman.coroutine.network.api.MovieApi
import com.mhuman.coroutine.network.model.Result

class MainAdapter :
    PagedListAdapter<Result, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemMovieListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_list,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = getItem(position)
        user?.let {
            holder as ViewHolder
            holder.bind(it)
        }
    }

    class ViewHolder(private val item: ItemMovieListBinding) :

        RecyclerView.ViewHolder((item.root)) {
        fun bind(list: Result) {
            setIsRecyclable(false)
            item.imageViewPosterImage.loadUrl(MovieApi.API_BASE_IMAGE_PATH_URL + list.posterPath)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem == newItem
        }
    }
}

