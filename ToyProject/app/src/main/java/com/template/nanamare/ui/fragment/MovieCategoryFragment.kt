package com.template.nanamare.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.template.nanamare.R
import com.template.nanamare.adapter.MovieAdapter
import com.template.nanamare.base.navigator.BaseNavigator
import com.template.nanamare.base.ui.BaseFragment
import com.template.nanamare.base.ui.BaseViewHolder
import com.template.nanamare.data.enum.RequestMovieApiType
import com.template.nanamare.data.vo.Movie
import com.template.nanamare.databinding.ItemMovieBinding
import com.template.nanamare.databinding.MovieCategoryFragmentBinding
import com.template.nanamare.decoration.GridSpacingItemDecoration
import com.template.nanamare.network.response.GenreResponse
import com.template.nanamare.network.response.MovieResponse
import com.template.nanamare.vm.MovieCategoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieCategoryFragment(private val genre: GenreResponse.Genre) :
    BaseFragment<MovieCategoryFragmentBinding>(R.layout.movie_category_fragment), BaseNavigator {

    private val movieCategoryViewModel by viewModel<MovieCategoryViewModel> {
        parametersOf(this)
    }
    private val column by lazy { resources.getInteger(R.integer.grid_column) }
    private val space by lazy { resources.getDimension(R.dimen.grid_space).toInt() }

    private val movieAdapter by lazy { MovieAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            tvLabel.text = getString(R.string.desc_movie, genre.name)
            rv.run {
                adapter = movieAdapter
                layoutManager = GridLayoutManager(context, column)
                addItemDecoration(GridSpacingItemDecoration(column, space, false))
                ViewCompat.setNestedScrollingEnabled(this, false)
            }

            movieCategoryViewModel.requestDiscoverMovies(genre.id, RequestMovieApiType.DISCOVER)
        }

        movieCategoryViewModel.liveMovies.observe(viewLifecycleOwner, Observer {
            it?.observe(viewLifecycleOwner, Observer {
                movieAdapter.submitList(it)
            }) ?: run {
                movieAdapter.submitList(null)
            }
        })

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchView.clearFocus()
        query?.let {
            movieCategoryViewModel.searchMovies(query, RequestMovieApiType.SEARCH)
            return true
        } ?: run {
            Toast.makeText(context, R.string.hint_search, Toast.LENGTH_SHORT).show()
            return false
        }
    }

    override fun closeSearchView() {
        super.closeSearchView()
        movieCategoryViewModel.requestDiscoverMovies(genre.id, RequestMovieApiType.DISCOVER)
    }

}