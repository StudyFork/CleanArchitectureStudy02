package com.mhuman.coroutine.ui

import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.mhuman.coroutine.R
import com.mhuman.coroutine.base.BaseActivity
import com.mhuman.coroutine.databinding.ActivityMainBinding
import com.mhuman.coroutine.network.model.Result
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel by viewModel<MainViewModel>()
    private val adapter: MainAdapter by lazy { MainAdapter() }

    override fun initializeUI() {
        registerEvent()
        viewModel.getPopularData()

        val manager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerViewMovieList.layoutManager = manager
        binding.recyclerViewMovieList.adapter = adapter
    }

    private fun registerEvent() {
        with(viewModel) {
            getPagedListLiveData().observe(
                this@MainActivity,
                object : Observer<PagedList<Result>> {
                    override fun onChanged(t: PagedList<Result>?) {
                        adapter.submitList(t)
                    }
                })

            liveNetworkErrors.observe(this@MainActivity, Observer {
                showToastMessage(it)
            })
        }
    }
}
