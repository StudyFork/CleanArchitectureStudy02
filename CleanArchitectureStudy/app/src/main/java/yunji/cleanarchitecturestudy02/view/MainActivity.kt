package yunji.cleanarchitecturestudy02.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import yunji.cleanarchitecturestudy02.R
import yunji.cleanarchitecturestudy02.adapter.MoviePagedRecyclerAdapter
import yunji.cleanarchitecturestudy02.base.BaseActivity
import yunji.cleanarchitecturestudy02.databinding.ActivityMainBinding
import yunji.cleanarchitecturestudy02.listener.OnItemClickListener
import yunji.cleanarchitecturestudy02.listener.OnSingleClickListener
import yunji.cleanarchitecturestudy02.model.repository.MovieRepository
import yunji.cleanarchitecturestudy02.model.response.Movie
import yunji.cleanarchitecturestudy02.util.showToast
import yunji.cleanarchitecturestudy02.viewmodel.MainViewModel
import yunji.cleanarchitecturestudy02.viewmodel.MainViewModelFactory

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val movieRecyclerAdapter = MoviePagedRecyclerAdapter()
    private val mainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(MovieRepository))[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        initView()
        observeUiData()
    }

    private fun initAdapter() {
        movieRecyclerAdapter.apply {
            onItemClickListener = OnSingleClickListener.wrap(
                object : OnItemClickListener<Movie> {
                    override fun onClick(item: Movie) {
                        showToast(item.toString())
                    }
                })
        }
    }

    private fun initView() {
        binding.apply {
            viewModel = mainViewModel
            lifecycleOwner = this@MainActivity
            rvMain.adapter = movieRecyclerAdapter
        }

        mainViewModel.initMovieData()
    }

    private fun observeUiData() {
        with(mainViewModel) {
            msgText.observe(this@MainActivity, Observer { showToast(it) })
        }
    }
}
