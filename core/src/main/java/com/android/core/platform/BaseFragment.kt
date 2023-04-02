package com.android.core.platform

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.android.core.extensions.EMPTY

abstract class BaseFragment : Fragment(), PageConfigurationHandler {

    private var pageAppearanceConfigurationListener: PageAppearanceConfigurationListener? = null
    private var pageAppearanceConfiguration: PageAppearanceConfiguration? = null
    private var pageChangeListener: PageChangeListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        pageAppearanceConfiguration = PageAppearanceConfiguration()
        pageAppearanceConfigurationListener = context as? PageAppearanceConfigurationListener
        pageChangeListener = context as? PageChangeListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pageAppearanceConfiguration?.let {
            it.setUpPageAppearance(this)
            pageAppearanceConfigurationListener?.configurePageAppearance(it)
        }
    }

    override fun onDetach() {
        pageAppearanceConfiguration = null
        pageAppearanceConfigurationListener = null
        pageChangeListener = null
        super.onDetach()
    }

    override fun isToolbarRequired() = true

    override fun getPageTitle() = String.EMPTY
}
