package com.example.toyproject.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.toyproject.R
import com.example.toyproject.data.entity.SearchMovieData
import com.example.toyproject.databinding.ItemMainBinding

class MainMoviePostingRecyclerAdapter :
    RecyclerView.Adapter<MainMoviePostingRecyclerAdapter.ViewHolder>() {
    private val movies: ArrayList<SearchMovieData> = ArrayList()

    fun setItemList(movies: ArrayList<SearchMovieData>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemMainBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_main,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = movies.size


    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = movies[position]
        holder.bind(item)
    }


    inner class ViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener({
                // TODO: 2020-03-06 clickListener
            })
        }

        fun bind(movieData: SearchMovieData) {
            binding.movieData = movieData
        }
    }
}