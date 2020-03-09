package com.template.nanamare.di

import com.template.nanamare.data.repository.GenreRepository
import com.template.nanamare.data.repository.MovieRepository
import com.template.nanamare.data.source.GenreRemoteDataSourceImpl
import com.template.nanamare.data.source.MovieRemoteDataSourceImpl
import com.template.nanamare.data.source.impl.GenreDataSource
import com.template.nanamare.data.source.impl.MovieDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * usage
 * single { UserInfoRepository(get(DI_API_NO_AUTH), get(DI_API_AUTH), get(), get(), get()) as UserInfoDataSource }
 */
val dataSourceModel = module {
    single { GenreRemoteDataSourceImpl(get(named(DI_API_NO_AUTH))) }
    single { GenreRepository(get()) as GenreDataSource }

    single { MovieRemoteDataSourceImpl(get(named(DI_API_NO_AUTH))) }
    single { MovieRepository(get()) as MovieDataSource }
}