package com.android.home.presentation.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.android.core.extensions.EMPTY
import com.android.core.extensions.showToast
import com.android.core.extensions.textChanges
import com.android.core.platform.BaseFragment
import com.android.core.state.UiState
import com.android.core.utils.AppUtils
import com.android.domain.model.Content
import com.android.home.R
import com.android.home.databinding.FragmentSearchBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class SearchFragment : BaseFragment() {
    private val searchViewModel by viewModel<SearchViewModel>()
    private val appUtils by inject<AppUtils>()
    private lateinit var adapter: SearchRecyclerViewAdapter

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        initUI()
    }

    private fun initUI() {
        handleEditTextChanges()
        initHomeRecyclerView()
        binding.imageSearchCancel.setOnClickListener {
            binding.editTextSearch.setText(String.EMPTY)
        }
    }

    private fun handleEditTextChanges() {
        binding.editTextSearch.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                appUtils.hideSoftKeyboard(view)
                searchViewModel.search(view.text.toString())
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        binding.editTextSearch.textChanges()
            .filterNot { it.isNullOrBlank() || it.trim().length < SEARCH_MINIMUM_LENGTH }
            .debounce(DEBOUNCE_TIME)
            .onEach { query ->
                searchViewModel.search(query = query.toString())
            }
            .launchIn(lifecycleScope)
    }

    private fun setUpObserver() {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBarSearch.visibility = View.GONE
                            addItemsToRecyclerView(it.data.first, it.data.second)
                        }
                        is UiState.Loading -> {
                            binding.progressBarSearch.visibility = View.VISIBLE
                        }
                        is UiState.Error -> {
                            binding.progressBarSearch.visibility = View.GONE
                            context.showToast(it.message)
                        }
                        is UiState.None -> {
                            // Nothing to do here
                        }
                    }
                }
            }
        }
    }

    private fun initHomeRecyclerView() {

        context?.let { context ->
            binding.recyclerViewSearch.run {
                layoutManager = GridLayoutManager(
                    context,
                    resources.getInteger(R.integer.movies_grid_column_count),
                    GridLayoutManager.VERTICAL,
                    false
                )
                this@SearchFragment.adapter = SearchRecyclerViewAdapter(
                    query = String.EMPTY,
                    items = listOf()
                )
                adapter = this@SearchFragment.adapter
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addItemsToRecyclerView(query: String, movieList: List<Content>) {
        adapter.updateData(query, movieList)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun isToolbarRequired() = false

    companion object {
        private const val DEBOUNCE_TIME = 300L
        private const val SEARCH_MINIMUM_LENGTH = 3
    }
}
