package com.android.core.platform

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.core.extensions.addFragment
import com.android.core.extensions.replaceFragment
import com.android.core.navigation.NavigationManager
import org.koin.android.ext.android.inject

abstract class BaseActivity :
    AppCompatActivity(), PageAppearanceConfigurationListener, PageChangeListener {

    protected val navigationManager: NavigationManager by inject()

    override fun addFragment(
        fragment: Fragment,
        addToBackStack: Boolean,
        tag: String?
    ) {
        if (isFinishing) return
        supportFragmentManager.addFragment(
            containerId = getFrameContainer(),
            fragment = fragment,
            addToBackStack = addToBackStack,
            tag = tag
        )
    }

    override fun replaceFragment(
        fragment: Fragment,
        addToBackStack: Boolean,
        tag: String?
    ) {
        if (isFinishing) return
        supportFragmentManager.replaceFragment(
            containerId = getFrameContainer(),
            fragment = fragment,
            addToBackStack = addToBackStack,
            tag = tag
        )
    }

    protected abstract fun getFrameContainer(): Int
}
