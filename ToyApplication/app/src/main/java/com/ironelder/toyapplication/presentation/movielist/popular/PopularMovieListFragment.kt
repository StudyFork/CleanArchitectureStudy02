package com.ironelder.toyapplication.presentation.movielist.popular


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ironelder.toyapplication.R
import com.ironelder.toyapplication.common.utils.IMAGE_BASE_URL
import com.ironelder.toyapplication.common.components.MovieListAdapter
import com.ironelder.toyapplication.data.api.NetworkServiceApi
import com.ironelder.toyapplication.data.models.movielist.MovieListModel
import com.ironelder.toyapplication.data.models.movielist.MovieResultModel
import kotlinx.android.synthetic.main.fragment_popular_movie_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularMovieListFragment : Fragment() {

    private val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)
    private val movieList = arrayListOf<MovieResultModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_movie_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pb_loading.visibility = View.VISIBLE
        rv_movie_list.adapter =
            MovieListAdapter()
        coroutineScope.launch {
            getMovieList()
        }
    }

    fun getMovieList() {
        NetworkServiceApi.movieServiceApi.getMovieList("popular").enqueue(
            object : Callback<MovieListModel> {
                override fun onFailure(call: Call<MovieListModel>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<MovieListModel>,
                    response: Response<MovieListModel>
                ) {
                    if (response.body()?.movieResultModels != null) {
                        movieList.addAll(mappingImageUrl((response.body() as MovieListModel).movieResultModels))
                        (rv_movie_list.adapter as MovieListAdapter).setMovieList(movieList)
                        pb_loading.visibility = View.GONE
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
