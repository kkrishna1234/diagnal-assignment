package com.android.diagnalmovies

import android.os.Bundle
import androidx.core.view.isVisible
import com.android.core.platform.BaseActivity
import com.android.core.platform.PageAppearanceConfiguration
import com.android.diagnalmovies.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        if (savedInstanceState == null) {
            navigateToHome()
        }
    }

    override fun configurePageAppearance(configuration: PageAppearanceConfiguration) {
        with(configuration) {
            binding.toolbarContainer.toolbarTitle?.text = pageTitle
            if (isToolbarRequired) {
                binding.viewToolbarShadow.isVisible = true
                supportActionBar?.show()
            } else {
                binding.viewToolbarShadow.isVisible = false
                supportActionBar?.hide()
            }
        }
    }

    private fun initUI() {
        setSupportActionBar(binding.toolbarContainer.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbarContainer.imageBack.setOnClickListener { onBackPressed() }
        binding.toolbarContainer.imageSearch.setOnClickListener {
            navigationManager.navigateToSearch(
                this
            )
        }
    }

    private fun navigateToHome() {
        navigationManager.navigateToHome(this)
    }

    override fun getFrameContainer() = R.id.container
}
