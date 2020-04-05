package com.example.movieapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieapplication.domain.GetPopularMovieUseCase
import com.example.movieapplication.domain.model.MovieEntity
import com.example.movieapplication.domain.model.mapToPresenter
import com.example.movieapplication.domain.repository.MovieRepository
import com.example.movieapplication.domain.result.ResultWrapper
import com.example.movieapplication.presenter.model.Movie
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

class GetPopularMovieUseCaseTest {

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    val dispatcher = TestCoroutineScopeRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var appProvider: AppProvider

    private lateinit var getPopularMovieUseCase: GetPopularMovieUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getPopularMovieUseCase = GetPopularMovieUseCase(movieRepository, appProvider)
    }

    @Test
    fun `이미지 간격을 제외한 디바이스 가로 길이에 맞춰 2 대 3 비율로 이미지 사이즈 결정`() {
        runBlocking {
            val page = 1
            val deviceWidth = 130
            val imageMargin = 10f

            //given
            val movieEntity = MovieEntity(10, listOf(MovieEntity.MovieItemEntity()))

            `when`(movieRepository.getPopularMovie(page)).thenReturn(movieEntity)
            `when`(appProvider.getDeviceWidth()).thenReturn(deviceWidth)
            `when`(appProvider.getDimens(R.dimen.item_movie_horizontal_margin)).thenReturn(
                imageMargin
            )

            //when
            val result = getPopularMovieUseCase.get(page)

            //then
            val movie = Movie(movieEntity.totalPages, movieEntity.movies.map {
                it.mapToPresenter(50, 75)
            })

            Assert.assertEquals(result, ResultWrapper.Success(movie))

        }
    }
}