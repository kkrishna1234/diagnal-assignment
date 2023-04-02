package com.android.core.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.core.platform.PageChangeListener

class Navigator(private val links: Map<Screen, Class<Fragment>>) {

    fun navigate(
        pageChangeListener: PageChangeListener,
        destination: Screen,
        arguments: Bundle? = null,
        addToBackstack: Boolean = false
    ) {
        val destinationFragment =
            links[destination]?.newInstance()?.apply { setArguments(arguments) }
        destinationFragment?.let { fragment ->
            pageChangeListener.replaceFragment(
                fragment,
                addToBackStack = addToBackstack
            )
        }
    }
}
