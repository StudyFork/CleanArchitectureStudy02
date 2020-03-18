package com.ironelder.toyapplication.presentation.movielist.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.ironelder.toyapplication.R
import com.ironelder.toyapplication.data.api.NetworkServiceApi
import kotlinx.coroutines.*
import java.lang.Exception

class SearchMovieListFragment : Fragment() {

    companion object {
        fun newInstance() = SearchMovieListFragment()
    }

    private lateinit var viewModel: SearchMovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_movie_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchMovieListViewModel::class.java)
        showData()
    }
    private fun showData() {
        viewModel.searchResultData.observe(viewLifecycleOwner, Observer {
            Log.d("ironelderLog", "response = $it")
        })
    }

}
