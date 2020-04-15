package com.egiwon.moviesearch.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer

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
        binding.initAdapter()
        addObserves()
    }

    open fun VDB.initAdapter() = Unit

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