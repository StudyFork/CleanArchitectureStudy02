package com.example.movieapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieapplication.domain.GetMovieDetailWithCreditUseCase
import com.example.movieapplication.domain.model.CreditEntity
import com.example.movieapplication.domain.model.MovieDetailEntity
import com.example.movieapplication.domain.repository.CreditRepository
import com.example.movieapplication.domain.repository.MovieRepository
import com.example.movieapplication.domain.result.ResultWrapper
import com.example.movieapplication.presenter.model.MovieDetail
import com.example.movieapplication.utils.DataUtil
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

class GetMovieDetailWithCreditUseCaseTest {

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
    private lateinit var creditRepository: CreditRepository

    @Mock
    private lateinit var appProvider: AppProvider

    private lateinit var getMovieDetailWithCreditUseCase: GetMovieDetailWithCreditUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getMovieDetailWithCreditUseCase =
            GetMovieDetailWithCreditUseCase(movieRepository, creditRepository, appProvider)
    }

    @Test
    fun `Movie와 Credit 데이터를 합쳐서 전달`() {
        runBlocking {
            val movieId = 1

            //given
            val movieDetailEntity = MovieDetailEntity(
                1,
                "movie path",
                "study fork",
                "clean architecture",
                "2020-01-01",
                10.0,
                9999
            )
            val creditEntity = CreditEntity(
                listOf(CreditEntity.CastEntity("profile path", "blackjin"))
            )

            `when`(movieRepository.getMovieDetail(movieId)).thenReturn(movieDetailEntity)
            `when`(creditRepository.getCredits(movieId)).thenReturn(creditEntity)

            //when
            val result = getMovieDetailWithCreditUseCase.get(movieId)

            //then
            val movieDetail = MovieDetail(
                posterPath = movieDetailEntity.posterPath,
                title = movieDetailEntity.title,
                summary = movieDetailEntity.overview,
                releaseDate = DataUtil.getDate(movieDetailEntity.releaseDate),
                voteAverage = DataUtil.getPercent(movieDetailEntity.voteAverage),
                voteCount = DataUtil.getCommaCount(movieDetailEntity.voteCount),
                actors = creditEntity.cast.map {
                    MovieDetail.Actor(
                        profileUrl = it.profilePath,
                        name = it.name
                    )
                }
            )

            Assert.assertEquals(result, ResultWrapper.Success(movieDetail))

        }
    }
}