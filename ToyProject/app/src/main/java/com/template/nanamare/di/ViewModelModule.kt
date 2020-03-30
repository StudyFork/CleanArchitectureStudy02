package com.template.nanamare.di

import com.template.nanamare.base.navigator.BaseNavigator
import com.template.nanamare.vm.MainViewModel
import com.template.nanamare.vm.MovieCategoryViewModel
import com.template.nanamare.vm.MovieInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * example
 * definition
 * viewModel { (mockData: MockData, mockNavigator : MockNavigator) -> MockViewModel(get(), oneClickAccount, mockNavigator) }
 *
 * private val mockViewModel by viewModel<MockViewModel> {
 *   parametersOf(this, this, ....)
 * }
 */
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { (baseNavigator: BaseNavigator) -> MovieCategoryViewModel(get(), baseNavigator) }
    viewModel { MovieInfoViewModel(get(), get(), get()) }
}
