package com.android.diagnalmovies

import android.app.Application
import com.android.core.di.coreModule
import com.android.data.di.dataModule
import com.android.diagnalmovies.di.appModule
import com.android.domain.di.domainModule
import com.android.home.di.homeModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            if (BuildConfig.DEBUG) androidLogger(Level.DEBUG)
            androidContext(this@MoviesApplication)
            modules(
                appModule,
                dataModule,
                domainModule,
                coreModule,

                // Features
                homeModule
            )
        }
    }
}
