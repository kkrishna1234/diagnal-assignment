package com.android.home.di

import com.android.home.presentation.datasource.MoviesPagingSource
import com.android.home.presentation.HomeViewModel
import com.android.home.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel<HomeViewModel>()
    viewModel<SearchViewModel>()

    factory { MoviesPagingSource(get()) }
}
