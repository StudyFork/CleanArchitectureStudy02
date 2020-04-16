package com.template.nanamare.di

import com.template.nanamare.network.response.GenreResponse
import com.template.nanamare.ui.dialog.VideoFragment
import com.template.nanamare.ui.fragment.MovieCategoryFragment
import com.template.nanamare.ui.fragment.MovieFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * usage
 * setupKoinFragmentFactory() in activity before super.onCreate(savedInstanceState)
 */
val fragmentModule: Module = module {
    fragment(override = true) {
        MovieFragment()
    }
    fragment(override = true) { (movies: List<GenreResponse.Genre>) ->
        MovieFragment(movies)
    }
    fragment(override = true) {
        MovieCategoryFragment()
    }
    fragment(override = true) { (movie: GenreResponse.Genre) ->
        MovieCategoryFragment(movie)
    }
    fragment(override = true) { VideoFragment() }
    fragment(override = true) { (liveVideoUrl: String) -> VideoFragment(liveVideoUrl) }
}