package com.template.nanamare.ui.fragment

import android.os.Bundle
import android.view.View
import com.template.nanamare.R
import com.template.nanamare.base.ui.BaseFragment
import com.template.nanamare.base.ui.BaseViewPager
import com.template.nanamare.databinding.MovieFragmentBinding
import com.template.nanamare.network.response.GenreResponse
import com.template.nanamare.ui.activity.MainActivity

class MovieFragment(private val genres: List<GenreResponse.Genre>) :
    BaseFragment<MovieFragmentBinding>(R.layout.movie_fragment) {

    private val baseViewPager by lazy { BaseViewPager(childFragmentManager) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            for (genre in genres) {
                baseViewPager.addFragment(MovieCategoryFragment(genre), genre.name)
            }
            viewPager.adapter = baseViewPager
            (requireActivity() as MainActivity).tlLayout.setupWithViewPager(viewPager)
        }
    }


}