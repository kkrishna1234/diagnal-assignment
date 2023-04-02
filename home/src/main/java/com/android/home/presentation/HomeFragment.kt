package com.android.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.android.core.extensions.showToast
import com.android.core.platform.BaseFragment
import com.android.home.R
import com.android.home.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment() {
    private val homeViewModel by viewModel<HomeViewModel>()
    private val moviesAdapter by lazy {
        context?.let { MoviesRecyclerViewAdapter() }
    }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        setUpObservers()
    }

    private fun initUi() {
        binding.recyclerViewHome.run {
            val gridLayoutManager = GridLayoutManager(
                context,
                resources.getInteger(R.integer.movies_grid_column_count),
                GridLayoutManager.VERTICAL,
                false
            )
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val viewType = this@HomeFragment.moviesAdapter?.getItemViewType(position)
                    return if (viewType == MoviesRecyclerViewAdapter.VIEW_TYPE_LOADING) {
                        resources.getInteger(R.integer.loading_grid_column_count)
                    } else {
                        resources.getInteger(R.integer.movies_grid_column_count)
                    }
                }
            }
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = this@HomeFragment.moviesAdapter?.withLoadStateFooter(
                footer = MoviesLoadStateAdapter()
            )
        }
        setLoadStateListener()
    }

    private fun setLoadStateListener() {
        moviesAdapter?.addLoadStateListener { loadStates ->
            binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
            if (loadStates.refresh is LoadState.Error) context.showToast(getString(R.string.generic_error))
        }
    }

    private fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.movieListFlow.collectLatest { pagingData ->
                    moviesAdapter?.submitData(pagingData)
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun getPageTitle() = getString(R.string.romantic_comedy)
}
