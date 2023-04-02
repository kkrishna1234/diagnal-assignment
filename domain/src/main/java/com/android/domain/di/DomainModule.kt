package com.android.domain.di

import com.android.domain.usecase.GetMoviesUseCase
import com.android.domain.usecase.SearchMoviesUseCase
import org.koin.dsl.module

val domainModule = module {

    factory { GetMoviesUseCase(moviesRepository = get(), errorHandler = get()) }
    factory { SearchMoviesUseCase(moviesRepository = get(), errorHandler = get()) }
}
