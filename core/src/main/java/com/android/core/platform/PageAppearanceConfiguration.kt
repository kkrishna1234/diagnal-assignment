package com.android.core.platform

import com.android.core.extensions.EMPTY

class PageAppearanceConfiguration {
    var pageTitle: String = String.EMPTY
    var isToolbarRequired: Boolean = false

    fun setUpPageAppearance(configurationHandler: PageConfigurationHandler) {
        isToolbarRequired = configurationHandler.isToolbarRequired()
        pageTitle = configurationHandler.getPageTitle()
    }
}
