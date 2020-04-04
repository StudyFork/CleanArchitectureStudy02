package yunji.cleanarchitecturestudy02.adapter

import yunji.cleanarchitecturestudy02.R
import yunji.cleanarchitecturestudy02.base.BaseDiffUtilCallback
import yunji.cleanarchitecturestudy02.base.BasePagedRecyclerView
import yunji.cleanarchitecturestudy02.databinding.ItemMovieBinding
import yunji.cleanarchitecturestudy02.model.response.Movie

/*
 * Created by yunji on 10/03/2020
 */
class MoviePagedRecyclerAdapter : BasePagedRecyclerView<ItemMovieBinding, Movie>(
    movieDiffUtilCallback, R.layout.item_movie
) {

    companion object {
        private val movieDiffUtilCallback = object : BaseDiffUtilCallback<Movie>() {

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id
        }
    }
}