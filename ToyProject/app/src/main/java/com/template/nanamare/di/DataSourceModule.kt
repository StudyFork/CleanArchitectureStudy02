package com.template.nanamare.di

import com.template.nanamare.data.repository.GenreRepository
import com.template.nanamare.data.repository.MovieRepository
import com.template.nanamare.data.source.GenreRemoteDataSource
import com.template.nanamare.data.source.MovieRemoteDataSource
import com.template.nanamare.data.source.impl.GenreDataSourceImpl
import com.template.nanamare.data.source.impl.MovieDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * usage
 * single { UserInfoRepository(get(DI_API_NO_AUTH), get(DI_API_AUTH), get(), get(), get()) as UserInfoDataSource }
 */
val dataSourceModel = module {
    single { GenreRemoteDataSource(get(named(DI_API_NO_AUTH))) }
    single { GenreRepository(get()) as GenreDataSourceImpl }

    single { MovieRemoteDataSource(get(named(DI_API_NO_AUTH))) }
    single { MovieRepository(get()) as MovieDataSourceImpl }
}