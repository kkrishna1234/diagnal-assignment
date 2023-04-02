package com.android.core.di

import com.android.core.platform.AspectRatioHandler
import com.android.core.utils.AppUtils
import org.koin.dsl.module

val coreModule = module {
    single { AppUtils(get()) }
    factory { AspectRatioHandler() }
}
