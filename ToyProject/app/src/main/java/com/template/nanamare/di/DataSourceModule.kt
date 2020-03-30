package com.template.nanamare.di

import com.template.nanamare.data.repository.*
import com.template.nanamare.data.source.*
import com.template.nanamare.data.source.impl.*
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

    single { MovieVideoRemoteDataSourceImpl(get(named(DI_API_NO_AUTH))) }
    single { MovieVideoRepository(get()) as MovieVideoDataSource }

    single { MovieCreditRemoteDataSourceImpl(get(named(DI_API_NO_AUTH))) }
    single { MovieCreditRepository(get()) as MovieCreditDataSource }

    single { MovieDetailRemoteDataSourceImpl(get(named(DI_API_NO_AUTH))) }
    single { MovieDetailRepository(get()) as MovieDetailDataSource }
}