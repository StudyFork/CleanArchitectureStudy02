package com.example.movieapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieapplication.data.model.ResultWrapper
import com.example.movieapplication.data.repository.MovieRepository
import com.example.movieapplication.presenter.model.Movie
import com.example.movieapplication.presenter.model.MovieItem
import com.example.movieapplication.presenter.movie.MovieViewModel
import com.example.movieapplication.utils.LiveDataTestUtil
import com.example.movieapplication.utils.TestCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MovieVewModelTest {

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    val dispatcher = TestCoroutineScopeRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        movieViewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun `영화 정보 받아오기 성공 케이스`() {
        runBlocking {
            //given
            val success = ResultWrapper.Success(Movie(movies = listOf(MovieItem())))
            `when`(movieRepository.getPopularMovie(1)).thenReturn(success)

            //when
            movieViewModel.loadMovie()

            //then
            val movies = LiveDataTestUtil.getValue(movieViewModel.movies)
            val moviesVisibility = LiveDataTestUtil.getValue(movieViewModel.moviesVisibility)

            Assert.assertEquals(success.value.movies, movies)
            Assert.assertEquals(true, moviesVisibility)
        }
    }

    @Test
    fun `영화 정보 받아오기 HTTP 실패 케이스`() {
        runBlocking {
            //given
            val httpException = ResultWrapper.HttpException(404, "http error")
            `when`(movieRepository.getPopularMovie(1)).thenReturn(httpException)

            //when
            movieViewModel.loadMovie()

            //then
            val errorMessage = LiveDataTestUtil.getValue(movieViewModel.errorMessage)
            val moviesVisibility = LiveDataTestUtil.getValue(movieViewModel.moviesVisibility)

            Assert.assertEquals("http error", errorMessage)
            Assert.assertEquals(false, moviesVisibility)
        }
    }

    @Test
    fun `영화 정보 받아오기 Network 실패 케이스`() {
        runBlocking {
            //given
            val httpException = ResultWrapper.NetworkError()
            `when`(movieRepository.getPopularMovie(1)).thenReturn(httpException)

            //when
            movieViewModel.loadMovie()

            //then
            val errorMessage = LiveDataTestUtil.getValue(movieViewModel.errorMessage)
            val moviesVisibility = LiveDataTestUtil.getValue(movieViewModel.moviesVisibility)

            Assert.assertEquals("Network Error", errorMessage)
            Assert.assertEquals(false, moviesVisibility)
        }
    }

    @Test
    fun `데이터 갱신 시 page cnt 증가 케이스`() {
        runBlocking {
            //given
            val success1 =
                ResultWrapper.Success(Movie(totalPages = 10, movies = listOf(MovieItem(id = 1))))
            `when`(movieRepository.getPopularMovie(1)).thenReturn(success1)

            val success2 =
                ResultWrapper.Success(Movie(totalPages = 10, movies = listOf(MovieItem(id = 2))))
            `when`(movieRepository.getPopularMovie(2)).thenReturn(success2)

            val success3 =
                ResultWrapper.Success(Movie(totalPages = 10, movies = listOf(MovieItem(id = 3))))
            `when`(movieRepository.getPopularMovie(3)).thenReturn(success3)

            //when & then
            movieViewModel.loadMovie()

            val movies1 = LiveDataTestUtil.getValue(movieViewModel.movies)
            Assert.assertEquals(success1.value.movies, movies1)

            movieViewModel.addMovie()

            val movies2 = LiveDataTestUtil.getValue(movieViewModel.movies)
            Assert.assertEquals(success2.value.movies, movies2)

            movieViewModel.addMovie()

            val movies3 = LiveDataTestUtil.getValue(movieViewModel.movies)
            Assert.assertEquals(success3.value.movies, movies3)
        }
    }

    @Test
    fun `데이터 갱신 시 total pages 초과 케이스`() {
        runBlocking {
            //given
            val success =
                ResultWrapper.Success(Movie(totalPages = 1, movies = listOf(MovieItem(id = 1))))
            `when`(movieRepository.getPopularMovie(1)).thenReturn(success)

            //when
            movieViewModel.loadMovie()
            movieViewModel.addMovie()

            //then
            val toast =
                LiveDataTestUtil.getValue(movieViewModel.toastLiveData).getContentIfNotHandled()
            Assert.assertEquals("Last Page", toast)
        }
    }
}