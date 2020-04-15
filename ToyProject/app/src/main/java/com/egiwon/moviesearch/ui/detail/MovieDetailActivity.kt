package com.egiwon.moviesearch.ui.detail

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.egiwon.moviesearch.BR
import com.egiwon.moviesearch.R
import com.egiwon.moviesearch.base.BaseActivity
import com.egiwon.moviesearch.base.BaseIdentifier
import com.egiwon.moviesearch.base.BaseListAdapter
import com.egiwon.moviesearch.base.BaseViewModel
import com.egiwon.moviesearch.databinding.ActivityMovieDetailBinding
import com.egiwon.moviesearch.databinding.ItemCastBinding
import com.egiwon.moviesearch.ui.MainActivity
import com.egiwon.moviesearch.ui.model.MovieCastViewObject
import com.egiwon.moviesearch.ui.model.MovieTrailerViewObject
import com.egiwon.moviesearch.ui.preview.PreviewDialog
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
            vm = viewModel
        }
        observingViewModel()
    }

    private fun showDialog(movieTrailer: MovieTrailerViewObject) {
        val fragmentManager = supportFragmentManager
        val newFragment = PreviewDialog()

        if (movieTrailer.trailers.isNotEmpty()) {
            newFragment.arguments = bundleOf(KEY_TRAILER to movieTrailer.trailers[0].key)
        }

        newFragment.show(fragmentManager, "dialog")
    }

    override fun ActivityMovieDetailBinding.initAdapter() {
        rvCreditCast.adapter =
            object : BaseListAdapter<MovieCastViewObject, ItemCastBinding>(
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
            (binding.rvCreditCast.adapter as? BaseListAdapter<BaseIdentifier, *>)
                ?.replaceAll(it)
        })

        viewModel.movieTrailerInfo.observe(this, Observer {
            showDialog(it)
        })
    }


    companion object {
        const val KEY_TRAILER = "key_trailer"
    }
}