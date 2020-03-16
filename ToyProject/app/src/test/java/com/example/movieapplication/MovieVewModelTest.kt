package com.example.movieapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieapplication.data.repository.MovieRepository
import com.example.movieapplication.presenter.movie.MovieViewModel
import com.example.movieapplication.utils.TestCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MovieVewModelTest {

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = TestCoroutineScopeRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieViewModel: MovieViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }
}