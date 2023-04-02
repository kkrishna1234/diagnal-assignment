package com.android.diagnalmovies.di

import androidx.fragment.app.Fragment
import com.android.core.navigation.NavigationManager
import com.android.core.navigation.Navigator
import com.android.core.navigation.Screen
import com.android.home.presentation.HomeFragment
import com.android.home.presentation.search.SearchFragment
import org.koin.dsl.module

val appModule = module {
    single {
        mapOf<Screen, Class<out Fragment>>(
            Screen.HOME to HomeFragment::class.java,
            Screen.SEARCH to SearchFragment::class.java
        )
    }

    single { Navigator(get()) }
    single { NavigationManager(navigator = get()) }
}
