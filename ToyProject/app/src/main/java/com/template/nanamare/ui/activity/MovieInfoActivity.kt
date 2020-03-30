package com.template.nanamare.ui.activity

import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.template.nanamare.BR
import com.template.nanamare.BuildConfig.VIDEO_URL
import com.template.nanamare.R
import com.template.nanamare.base.ui.BaseActivity
import com.template.nanamare.base.ui.SimpleRecyclerView
import com.template.nanamare.data.vo.Actor
import com.template.nanamare.databinding.ItemActorBinding
import com.template.nanamare.databinding.MovieInfoActivityBinding
import com.template.nanamare.network.NetworkState
import com.template.nanamare.network.response.MovieCreditResponse
import com.template.nanamare.network.response.MovieVideoResponse
import com.template.nanamare.ui.anim.SimpleAnimation
import com.template.nanamare.utils.Logger
import com.template.nanamare.vm.MovieInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MovieInfoActivity : BaseActivity<MovieInfoActivityBinding>(R.layout.movie_info_activity) {

    private val movieInfoViewModel by viewModel<MovieInfoViewModel>()

    private val actorSimpleAdapter by lazy {
        object :
            SimpleRecyclerView.Adapter<Actor, ItemActorBinding>(R.layout.item_actor, BR.actor) {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): SimpleRecyclerView.ViewHolder<ItemActorBinding> {
                return super.onCreateViewHolder(parent, viewType).apply {

                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.run {
            vm = movieInfoViewModel
            actorAdapter = actorSimpleAdapter
            movieInfoViewModel.movie = intent.extras?.getParcelable(EXTRA_MOVIE_ITEM)
            initAnim()
            initActor()
        }

        movieInfoViewModel.run {
            liveMovieVideoState.observe(this@MovieInfoActivity, Observer {
                when (it) {
                    is NetworkState.Init -> hideLoadingPopup()
                    is NetworkState.Loading -> showLoadingPopup()
                    is NetworkState.Success<MovieVideoResponse> -> getUrl(it)
                    is NetworkState.Error -> showToast(it.throwable.toString())
                    is NetworkState.ServerError -> showToast(it.toString())
                }
            })

            liveMovieCreditState.observe(this@MovieInfoActivity, Observer {
                when (it) {
                    is NetworkState.Init -> hideLoadingPopup()
                    is NetworkState.Loading -> showLoadingPopup()
                    is NetworkState.Success<MovieCreditResponse> -> {
                        liveLoading.value = false
                        initActorAdapter(it)
                    }
                    is NetworkState.Error -> showToast(it.throwable.toString())
                    is NetworkState.ServerError -> showToast(it.toString())
                }
            })
        }

    }

    private fun initActorAdapter(it: NetworkState.Success<MovieCreditResponse>) {
        movieInfoViewModel.liveActor.value = it.item.cast.map { Actor(it.name, it.profilePath) }
    }

    private fun getUrl(it: NetworkState.Success<MovieVideoResponse>) {
        Logger.d(TAG, it.toString())
        it.item.results.asSequence()
            .filter { "YouTube".toLowerCase(Locale.getDefault()) == it.site.toLowerCase(Locale.getDefault()) }
            .reduce { acc, result -> if (acc.size > result.size) acc else result }.let {
                Logger.d(
                    TAG,
                    Uri.parse(VIDEO_URL).buildUpon().appendQueryParameter("v", it.key).toString()
                )
            }
    }

    private fun initActor() {
        movieInfoViewModel.run {
            movie?.let {
                getMovieCredit(it.id)
            }
        }
    }

    private fun MovieInfoActivityBinding.initAnim() {
        ivPoster.startAnimation(SimpleAnimation.leftToRight)
        movieInfoBody.root.startAnimation(SimpleAnimation.rightToLeft)
        tvNext.startAnimation(SimpleAnimation.bottomToTop)
        movieSummaryBody.root.startAnimation(SimpleAnimation.rightToLeft)
        movieActorInfoBody.root.startAnimation(SimpleAnimation.leftToRight)
    }


    companion object {
        const val EXTRA_MOVIE_ITEM = "EXTRA_MOVIE_ITEM"
    }

}