package com.android.core.platform

import androidx.fragment.app.Fragment

interface PageChangeListener {

    fun addFragment(
        fragment: Fragment,
        addToBackStack: Boolean = false,
        tag: String? = fragment::class.simpleName
    )

    fun replaceFragment(
        fragment: Fragment,
        addToBackStack: Boolean = false,
        tag: String? = fragment::class.simpleName
    )
}
