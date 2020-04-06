package com.mhuman.coroutine.di

import com.mhuman.coroutine.data.remote.PopularDataSource
import com.mhuman.coroutine.data.remote.PopularPagingFactory
import com.mhuman.coroutine.data.remote.PopularPagingRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { PopularDataSource(get()) }
    single { PopularPagingFactory(get()) }
    single { PopularPagingRepository() }
}