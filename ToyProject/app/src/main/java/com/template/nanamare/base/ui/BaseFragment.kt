package com.template.nanamare.base.ui


import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.template.nanamare.R
import com.template.nanamare.base.navigator.BaseNavigator
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<B : ViewDataBinding>(private val layoutId: Int) : Fragment(),
    SearchView.OnQueryTextListener, BaseNavigator {

    protected lateinit var binding: B
    protected val compositeDisposable = CompositeDisposable()

    protected lateinit var searchView: SearchView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.search, menu)
        // 검색
        val menuItem = menu.findItem(R.id.action_search)
        menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                closeSearchView()
                return true
            }
        })
        searchView = menuItem.actionView as SearchView
        searchView.queryHint = getString(R.string.hint_search)
        searchView.setOnQueryTextListener(this)
        val closeButton: View =
            searchView.findViewById(androidx.appcompat.R.id.search_close_btn)
        closeButton.setOnClickListener {
            menuItem.collapseActionView()
            closeSearchView()
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    open fun closeSearchView() {}

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }

    override fun networkError(errorCode: String) {
        (activity as? BaseActivity<*>)?.networkError(errorCode)
    }

    override fun errorHandling(errorCode: String) {
        (activity as? BaseActivity<*>)?.errorHandling(errorCode)
    }

    override fun showToast(resId: Int, error: Boolean) {
        context?.let {
            showToast(it.getString(resId))
        }
    }

    override fun showToast(msg: String, error: Boolean) {
        (activity as? BaseActivity<*>)?.showToast(msg)
    }

    fun showKeyboard() {
        Handler().postDelayed({
            (activity as? BaseActivity<*>)?.showKeyboard()
        }, 50)
    }

    override fun hideKeyboard() {
        (activity as? BaseActivity<*>)?.hideKeyboard()
    }

    override fun showLoadingPopup() {
        (activity as? BaseActivity<*>)?.showLoadingPopup()
    }

    override fun hideLoadingPopup() {
        (activity as? BaseActivity<*>)?.hideLoadingPopup()
    }

    override fun onDetach() {
        (activity as? BaseActivity<*>)?.hideLoadingPopup()
        super.onDetach()
    }

    override fun logFirebaseEvent(name: String, params: Bundle?) {
        (activity as? BaseActivity<*>)?.logFirebaseEvent(name, params)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}
