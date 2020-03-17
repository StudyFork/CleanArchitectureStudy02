package com.egiwon.moviesearch.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity(layoutResId) {

    protected abstract val viewModel: VM

    protected lateinit var binding: B
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.lifecycleOwner = this
        addObserves()
    }

    protected open fun addObserves() {
        viewModel.showErrorTextResId.observe(this, Observer { showToast(it) })
    }

    protected fun bind(action: B.() -> Unit) {
        binding.run(action)
    }

    protected fun showToast(textResId: Int) {
        Toast.makeText(this, textResId, Toast.LENGTH_SHORT).show()
    }

}