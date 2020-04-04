package yunji.cleanarchitecturestudy02.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import yunji.cleanarchitecturestudy02.model.repository.MovieRepository

/*
 * Created by yunji on 11/03/2020
 */
@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val movieRepository: MovieRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = MainViewModel(movieRepository) as T
}