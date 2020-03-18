package com.egiwon.moviesearch.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import com.egiwon.moviesearch.BR
import com.egiwon.moviesearch.R
import com.egiwon.moviesearch.base.BaseActivity
import com.egiwon.moviesearch.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind {
            vm = viewModel
            rvMovieList.adapter = MainMovieAdapter(bindingId = BR.movie, viewModel = viewModel)
            rvMovieList.setHasFixedSize(true)
        }

        viewModel.loadPopularMovies()

        viewModel.resultMovieList.observe(this, Observer {
            (binding.rvMovieList.adapter as? MainMovieAdapter)?.run {
                replaceAll(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val nightMode = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu?.findItem(R.id.night_mode)?.setIcon(R.drawable.ic_sunny)
        } else {
            menu?.findItem(R.id.night_mode)?.setIcon(R.drawable.ic_brightness)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.night_mode -> {
                val nightMode = AppCompatDelegate.getDefaultNightMode()
                if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                recreate()
            }

        }
        return true
    }
}
