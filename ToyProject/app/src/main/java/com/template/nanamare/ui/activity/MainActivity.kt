package com.template.nanamare.ui.activity

import android.annotation.SuppressLint
import android.app.ActionBar
import android.os.Bundle
import android.view.Gravity
import androidx.lifecycle.Observer
import com.template.nanamare.R
import com.template.nanamare.base.ui.BaseActivity
import com.template.nanamare.databinding.MainActivityBinding
import com.template.nanamare.ext.replaceFragmentInActivity
import com.template.nanamare.network.NetworkState
import com.template.nanamare.network.response.GenreResponse
import com.template.nanamare.ui.fragment.MovieFragment
import com.template.nanamare.vm.MainViewModel
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainActivityBinding>(R.layout.main_activity) {

    private val mainViewModel by viewModel<MainViewModel>()

    val tlLayout
        get() = binding.tlMovie

    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)

        binding.run {
            initToolbar()
        }

        mainViewModel.run {
            requestMovieGenre()
            liveGenreNetworkState.observe(this@MainActivity, Observer {
                when (it) {
                    is NetworkState.Init -> hideLoadingPopup()
                    is NetworkState.Loading -> showLoadingPopup()
                    is NetworkState.Success<GenreResponse> -> replaceFragmentInActivity(MovieFragment(it.item.genres), R.id.flContent)
                    is NetworkState.Error -> showToast(it.throwable.toString())
                    is NetworkState.ServerError -> showToast(it.toString())
                }
            })
        }

    }

    // 헤더 CI 적용
    @SuppressLint("WrongConstant", "InflateParams")
    private fun MainActivityBinding.initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayShowCustomEnabled(true)
            it.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            it.setCustomView(
                layoutInflater.inflate(R.layout.appbar_title, null),
                androidx.appcompat.app.ActionBar.LayoutParams(
                    ActionBar.LayoutParams.WRAP_CONTENT,
                    ActionBar.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER
                )
            )
        }
    }
}
