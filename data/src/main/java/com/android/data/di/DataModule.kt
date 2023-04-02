package com.android.data.di

import com.android.data.error.DataErrorHandler
import com.android.data.mappers.FileJsonMapper
import com.android.data.repository.MoviesDataRepository
import com.android.data.repository.datasource.MoviesLocalDataSource
import com.android.data.utils.FileUtils
import com.android.domain.error.ErrorHandler
import com.android.domain.repository.MoviesRepository
import com.google.gson.Gson
import org.koin.dsl.module

val dataModule = module {

    factory<MoviesRepository> { MoviesDataRepository(moviesLocalDataSource = get()) }
    factory { MoviesLocalDataSource(fileUtils = get(), gson = get(), get()) }
    single { provideGson() }
    single { FileUtils(context = get()) }
    single { FileJsonMapper() }
    factory<ErrorHandler> { DataErrorHandler() }
}

fun provideGson() = Gson()
