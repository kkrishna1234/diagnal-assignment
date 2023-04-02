package com.android.core.navigation

import com.android.core.platform.PageChangeListener

class NavigationManager(private val navigator: Navigator) {

    fun navigateToHome(pageChangeListener: PageChangeListener) {
        navigator.navigate(pageChangeListener, Screen.HOME)
    }

    fun navigateToSearch(pageChangeListener: PageChangeListener) {
        navigator.navigate(pageChangeListener, Screen.SEARCH, addToBackstack = true)
    }
}
