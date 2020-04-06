package com.example.movieapplication.presenter.model

import android.view.View
import android.widget.Toast

data class MovieDetail(
    val posterPath: String,
    val title: String,
    val summary: String,
    val releaseDate: String,
    val voteAverage: String,
    val voteCount: String,
    val actors: List<Actor>
) {
    data class Actor(
        val profileUrl: String,
        val name: String
    ) {

        fun onClick(view: View, name: String) {
            Toast.makeText(view.context, name, Toast.LENGTH_LONG).show()
        }
    }
}