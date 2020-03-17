package com.ironelder.toyapplication.presentation.movielist.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

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

    val coroutineExceptionHandler = CoroutineExceptionHandler{_, t -> {
        t.printStackTrace()
    }}


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchMovieListViewModel::class.java)
//        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
//            val response = NetworkServiceApi.movieServiceApi.getSearchMovie("Test")
//            withContext(Dispatchers.Main) {
//                try{
//                    Log.d("ironelderLog", "response = $response")
//                }
//                catch(e:Exception){
//                    Log.d("ironelderLog", "e = $e")
//                }
//            }
//        }
        showData()

    }
    private fun showData() {
        viewModel.data.observe(this.viewLifecycleOwner, Observer {
            Log.d("ironelderLog", "response = $it")
        })
    }

}
