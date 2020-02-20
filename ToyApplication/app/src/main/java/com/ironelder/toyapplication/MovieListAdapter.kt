package com.ironelder.toyapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ironelder.toyapplication.model.movielist.MovieResultModel
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieListItemHolder>() {

    private val movieList = arrayListOf<MovieResultModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListItemHolder {
        return MovieListItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieListItemHolder, position: Int) {
        holder.onBind(movieList[position])
    }

    fun setMovieList(list:List<MovieResultModel>){
        movieList.addAll(list)
        notifyDataSetChanged()
    }

    class MovieListItemHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val ivMoviePoster = view.iv_movie_poster
        private val tvMovieTitle = view.tv_movie_title

        fun onBind(item: MovieResultModel) {
            Glide.with(itemView.context).load(item.poster_path)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(ivMoviePoster)
            tvMovieTitle.text = item.title
        }
    }
}
