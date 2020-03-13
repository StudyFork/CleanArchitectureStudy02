package yunji.cleanarchitecturestudy02.adapter

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import yunji.cleanarchitecturestudy02.model.response.Movie

/*
 * Created by yunji on 13/03/2020
 */
@BindingAdapter("bindMovieItem")
fun bindPageItem(rv: RecyclerView, data: PagedList<Movie>?) {
    (rv.adapter as MoviePagedRecyclerAdapter).submitList(data)
}