package com.template.nanamare.di

import com.template.nanamare.network.api.movie.*
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

const val DI_API_AUTH = "DI_API_AUTH"
const val DI_API_NO_AUTH = "DI_API_NO_AUTH"

/**
 * usage
 * single(DI_API_AUTH) { (get(DI_RETROFIT) as Retrofit).create(MobileAppApi::class.java) }
 * single(DI_API_NO_AUTH) { (get(DI_RETROFIT_NO_AUTH) as Retrofit).create(MobileAppApi::class.java) }
 */
val apiModule = module {
    single(named(DI_API_NO_AUTH)) { (get(named(DI_RETROFIT_NO_AUTH)) as Retrofit).create(CreditAPI::class.java) }
    single(named(DI_API_NO_AUTH)) { (get(named(DI_RETROFIT_NO_AUTH)) as Retrofit).create(DiscoverAPI::class.java) }
    single(named(DI_API_NO_AUTH)) { (get(named(DI_RETROFIT_NO_AUTH)) as Retrofit).create(GenreAPI::class.java) }
    single(named(DI_API_NO_AUTH)) { (get(named(DI_RETROFIT_NO_AUTH)) as Retrofit).create(MovieAPI::class.java) }
    single(named(DI_API_NO_AUTH)) { (get(named(DI_RETROFIT_NO_AUTH)) as Retrofit).create(SearchAPI::class.java) }
}
