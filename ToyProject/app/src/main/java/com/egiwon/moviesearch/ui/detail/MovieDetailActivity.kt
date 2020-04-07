package com.egiwon.moviesearch.ui.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.egiwon.moviesearch.BR
import com.egiwon.moviesearch.R
import com.egiwon.moviesearch.base.BaseActivity
import com.egiwon.moviesearch.base.BaseRecyclerView
import com.egiwon.moviesearch.base.BaseViewModel
import com.egiwon.moviesearch.databinding.ActivityMovieDetailBinding
import com.egiwon.moviesearch.databinding.ItemCastBinding
import com.egiwon.moviesearch.ui.MainActivity
import com.egiwon.moviesearch.ui.model.MovieCastViewObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding, MovieDetailViewModel>(
    R.layout.activity_movie_detail
) {

    override val viewModel: MovieDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieId = intent.getIntExtra(MainActivity.KEY_MOVIE_ID, 0)
        viewModel.getMovieDetailInfo(movieId)

        bind {
            initAdapter()
        }

        observingViewModel()
    }

    override fun ActivityMovieDetailBinding.initAdapter() {
        rvCreditCast.adapter =
            object : BaseRecyclerView.BaseListAdapter<MovieCastViewObject, ItemCastBinding>(
                R.layout.item_cast,
                BR.castOfCharacter,
                mutableMapOf<Int?, BaseViewModel>().apply {
                    put(BR.vm, viewModel)
                }
            ) {}

        rvCreditCast.setHasFixedSize(true)
    }

    private fun observingViewModel() {
        viewModel.movieDetailInfo.observe(this, Observer {
            binding.movieDetailInfo = it
        })

        @Suppress("UNCHECKED_CAST")
        viewModel.movieCastList.observe(this, Observer {
            (binding.rvCreditCast.adapter as? BaseRecyclerView.BaseListAdapter<MovieCastViewObject, ItemCastBinding>)?.replaceAll(
                it
            )
        })
    }
}