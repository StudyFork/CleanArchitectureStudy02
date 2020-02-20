package com.ironelder.toyapplication


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ironelder.toyapplication.model.movielist.MovieListModel
import com.ironelder.toyapplication.model.movielist.MovieResultModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment() {

    private val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)
    private val movieList = arrayListOf<MovieResultModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("ironelderLog", "onActivityCreated")
        rv_movie_list.adapter = MovieListAdapter()
        rv_movie_list.layoutManager = GridLayoutManager(context, 6)
        (rv_movie_list.layoutManager as GridLayoutManager).spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return when(position % 5){
                    0,1,2 -> 2
                    3,4 -> 3
                    else -> 0
                }
            }
        }
        coroutineScope.launch {
            getMovieList()
        }
    }

    fun getMovieList() {
        NetworkServiceApi.movieServiceApi.getMovieList("popular").enqueue(
            object : Callback<MovieListModel> {
                override fun onFailure(call: Call<MovieListModel>, t: Throwable) {
                    Log.d("ironelderLog", "onFailure call = $call")
                }

                override fun onResponse(
                    call: Call<MovieListModel>,
                    response: Response<MovieListModel>
                ) {
                    Log.d("ironelderLog", "call = $call")
                    if (response.body()?.movieResultModels != null) {
                        movieList.addAll(mappingImageUrl((response.body() as MovieListModel).movieResultModels))
                        Log.d("ironelderLog", "movieList = $movieList")
                    }

                }
            }
        )
    }

    fun mappingImageUrl(movieList: List<MovieResultModel>) = movieList.map {
        MovieResultModel(
            adult = it.adult,
            backdrop_path = it.backdrop_path,
            genre_ids = it.genre_ids,
            id = it.id,
            original_language = it.original_language,
            original_title = it.original_title,
            overview = it.overview,
            popularity = it.popularity,
            poster_path = IMAGE_BASE_URL + it.poster_path,
            release_date = it.release_date,
            title = it.title,
            video = it.video,
            vote_average = it.vote_average,
            vote_count = it.vote_count
        )
    }


    override fun onDestroy() {
        super.onDestroy()
        completableJob.cancel()
    }
}
