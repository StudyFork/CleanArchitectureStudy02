package yunji.cleanarchitecturestudy02.adapter

import yunji.cleanarchitecturestudy02.R
import yunji.cleanarchitecturestudy02.base.BaseRecyclerView
import yunji.cleanarchitecturestudy02.databinding.ItemMovieBinding
import yunji.cleanarchitecturestudy02.model.response.Movie

/*
 * Created by yunji on 10/03/2020
 */
class MovieRecyclerAdapter : BaseRecyclerView<ItemMovieBinding, Movie>(R.layout.item_movie)