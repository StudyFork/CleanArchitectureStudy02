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

    single { MovieRemoteDataSourceImpl(get(named(DI_API_NO_AUTH)), get(named(DI_API_NO_AUTH))) }
    single { MovieRepository(get()) as MovieDataSource }

    single { VideoRemoteDataSourceImpl(get(named(DI_API_NO_AUTH))) }
    single { VideoRepository(get()) as VideoDataSource }

    single { CreditRemoteDataSourceImpl(get(named(DI_API_NO_AUTH))) }
    single { CreditRepository(get()) as CreditDataSource }

    single { DetailRemoteDataSourceImpl(get(named(DI_API_NO_AUTH))) }
    single { DetailRepository(get()) as DetailDataSource }
}