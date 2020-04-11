package com.god.taeiim.movieapp.ui.main

import androidx.lifecycle.viewModelScope
import com.god.taeiim.movieapp.base.BaseAction
import com.god.taeiim.movieapp.base.BaseViewModel
import com.god.taeiim.movieapp.base.BaseViewState
import com.god.taeiim.movieapp.data.model.Movie
import com.god.taeiim.movieapp.data.repository.MovieRepository
import com.god.taeiim.movieapp.ui.main.MainViewModel.Action
import com.god.taeiim.movieapp.ui.main.MainViewModel.Action.MovieListLoadingFailure
import com.god.taeiim.movieapp.ui.main.MainViewModel.Action.MovieListLoadingSuccess
import com.god.taeiim.movieapp.ui.main.MainViewModel.ViewState
import kotlinx.coroutines.launch


internal class MainViewModel(private val movieRepository: MovieRepository) :
    BaseViewModel<ViewState, Action>(ViewState()) {

    override fun onLoadData() {
        getMovieList()
    }

    private fun getMovieList() {
        viewModelScope.launch {
            movieRepository.getPopularMovies(1).also {
                if (it.isNotEmpty()) {
                    sendAction(MovieListLoadingSuccess(it))
                } else {
                    sendAction(MovieListLoadingFailure)
                }
            }
        }
    }

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is MovieListLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            movies = viewAction.movies
        )
        is MovieListLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            movies = listOf()
        )
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val movies: List<Movie> = listOf()
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        class MovieListLoadingSuccess(val movies: List<Movie>) : Action()
        object MovieListLoadingFailure : Action()
    }

}