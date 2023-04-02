package com.android.core.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.addFragment(
    @IdRes containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false,
    tag: String? = fragment::class.simpleName
) {
    with(beginTransaction()) {
        add(containerId, fragment, tag)
        if (addToBackStack) addToBackStack(tag)
        commit()
    }
}

fun FragmentManager.replaceFragment(
    @IdRes containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false,
    tag: String? = fragment::class.simpleName
) {
    with(beginTransaction()) {
        replace(containerId, fragment, tag)
        if (addToBackStack) addToBackStack(tag)
        commit()
    }
}
