package com.egiwon.moviesearch.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.egiwon.moviesearch.R

abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity(layoutResId) {

    protected abstract val viewModel: VM

    protected lateinit var binding: VDB
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.lifecycleOwner = this
        addObserves()
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

    private fun addObserves() {
        viewModel.showErrorTextResId.observe(this, Observer { showToast(it) })
    }

    protected fun bind(action: VDB.() -> Unit) {
        binding.run(action)
    }

    protected fun showToast(textResId: Int) {
        Toast.makeText(this, textResId, Toast.LENGTH_SHORT).show()
    }

}